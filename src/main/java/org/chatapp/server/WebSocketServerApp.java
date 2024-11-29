package org.chatapp.server;
import org.glassfish.tyrus.server.Server;



public class WebSocketServerApp {
    public static void main(String[] args) {
        // Specify the server details
        Server server = new Server("localhost", 1235, "/chat", null, ChatServer.class);

        try {
            // Start the server
            server.start();
            System.out.println("WebSocket server started...");
            System.in.read(); // Keep server running, press Enter to stop
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Stop the server
            server.stop();
        }
    }
}
