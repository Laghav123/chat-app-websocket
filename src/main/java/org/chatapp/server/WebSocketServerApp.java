package org.chatapp.server;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.tyrus.server.Server;



public class WebSocketServerApp {
    public static void main(String[] args) {


        try {

            HttpServer httpServer = HttpServer.createSimpleServer(null, 3000);
            HttpHandler staticHandler = new StaticHttpHandler("./react-build");
            httpServer.getServerConfiguration().addHttpHandler(staticHandler, "/");
            httpServer.start();


            Server server = new Server("localhost", 1235, "/chat", null, ChatServer.class);

            server.start();
            System.out.println("WebSocket server started...");
            System.in.read(); // Keep server running, press Enter to stop
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
