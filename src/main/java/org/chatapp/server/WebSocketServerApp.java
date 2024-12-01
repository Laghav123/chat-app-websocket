package org.chatapp.server;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;


public class WebSocketServerApp {
    public static void main(String[] args) {


        try {
            int port;
            if(System.getenv("PORT") != null){
                port = Integer.parseInt(System.getenv("PORT"));
            }
            else {
                port=8080;
            }
            Server server = new Server(port);

            ServletContextHandler servletContextHandler = new ServletContextHandler("");
            server.setHandler(servletContextHandler);

            JakartaWebSocketServletContainerInitializer.configure(servletContextHandler, (servletContext, container) ->
            {
                container.setDefaultMaxTextMessageBufferSize(128 * 1024);
                container.setDefaultMaxSessionIdleTimeout(600000); // 10 minutes
                container.addEndpoint(ChatServer.class);
            });
            servletContextHandler.addServlet(HttpFiller.class, "/");

            server.start();
            System.out.println("Service started on port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
