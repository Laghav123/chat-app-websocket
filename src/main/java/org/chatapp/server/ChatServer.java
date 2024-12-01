package org.chatapp.server;


import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.chatapp.message.Message;
import org.chatapp.message.MessageDecoder;
import org.chatapp.message.MessageEncoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(
        value = "/chatroom",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class ChatServer {

    private static Set<Session> clients = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        clients.add(session);
        System.out.println("New client connected: " + session.getId());

        String username = session.getRequestParameterMap().get("userName").get(0);
        users.put(session.getId(), username);
        broadcastMessage(new Message("Server", username+" has joined the chat.", String.valueOf(System.currentTimeMillis())));
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException {
        System.out.println("Message received from client: " + message);
        message.setFrom(users.get(session.getId()));
        message.setId(String.valueOf(System.currentTimeMillis()));
        broadcastMessage(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        clients.remove(session);
        System.out.println("Client disconnected: " + session.getId());
        broadcastMessage(new Message("Server", users.get(session.getId()) + " has left the chat.", String.valueOf(System.currentTimeMillis())));
        users.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error occurred in session " + (session != null ? session.getId() : "unknown") + ": " + throwable.getMessage());
    }

    private void broadcastMessage(Message message) {
        for (Session client : clients) {
            try {
                client.getBasicRemote().sendObject(message); // Encodes message using MessageEncoder
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }
}
