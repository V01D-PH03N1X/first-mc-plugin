package org.devcloud.testing.utils.webserver;

import com.sun.net.httpserver.*;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class WebServer {
    public static void startServer(Server server) throws IOException {
        HttpServer httpserver = HttpServer.create(new InetSocketAddress(8000), 0);
        httpserver.createContext("/send/message", new SendMessage(server));
        httpserver.start();
    }
}

class SendMessage implements HttpHandler {
    Server bukkitServer;
    public SendMessage(Server server) {
        bukkitServer = server;
    }
    public void handle(HttpExchange httpExchange) throws IOException {

        // Create Response
        URI uri = httpExchange.getRequestURI();
        String nameParam = getMessageParam(uri);

        StringBuilder response = new StringBuilder();
        response.append("Message Successfully Sent!");


        // Send Response
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();

        bukkitServer.getOnlinePlayers().forEach(player -> player.sendMessage(nameParam));
    }

    private @NotNull String getMessageParam(@NotNull URI uri)
    {
        String messageParam = "message";

        String message = "";
        String query = uri.getQuery();
        if (query != null) {
            String[] queryParams = query.split("&");
            for (String qParam : queryParams) {
                String[] param = qParam.split("=");
                if (param.length > 0) {
                    for (int i = 0; i < param.length; i++) {
                        if (messageParam.equalsIgnoreCase(param[0])) {
                            message = param[1];
                            break;
                        }
                    }
                }
            }
        }

        return message;
    }
}
