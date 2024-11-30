package org.chatapp.server;
import jakarta.websocket.server.ServerContainer;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.tyrus.container.grizzly.server.GrizzlyServerContainer;
import org.glassfish.tyrus.server.Server;



public class WebSocketServerApp {
    public static void main(String[] args) {


        try {
            int port = Integer.parseInt(System.getenv("PORT")); // Fetch Heroku-assigned port

            HttpServer httpServer = HttpServer.createSimpleServer(null, port);
//            HttpHandler staticHandler = new StaticHttpHandler("./react-build");
            httpServer.getServerConfiguration().addHttpHandler(new StaticHttpHandler() {
                @Override
                public void service(org.glassfish.grizzly.http.server.Request request,
                                    org.glassfish.grizzly.http.server.Response response) throws Exception {
                    response.setContentType("text/plain");
                    response.getWriter().write("This is a web socket server");
                }
            }, "/");
            httpServer.getServerConfiguration().addHttpHandler(new StaticHttpHandler() {
                @Override
                public void service(org.glassfish.grizzly.http.server.Request request,
                                    org.glassfish.grizzly.http.server.Response response) throws Exception {
                    response.setContentType("text/plain");
                    response.getWriter().write("This is a web socket server");
                }
            }, "/favicon.ico");

//            ServerContainer serverContainer = new

            httpServer.start();
//            System.out.println("Enabled System.in.read()");
//            System.in.read();

            // Add a shutdown hook to gracefully stop the server
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down server...");
                httpServer.shutdownNow();
            }));

            // Block the main thread indefinitely
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupt status
                System.err.println("Main thread interrupted, exiting...");
            }

//            Server server = new Server("localhost", port, "/", null, ChatServer.class);
//            server.start();
//            System.out.println("WebSocket server started...");
//            System.in.read(); // Keep server running, press Enter to stop
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
