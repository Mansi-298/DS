package web_service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleWeb {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Home Page
        server.createContext("/", new HttpHandler() {

            public void handle(HttpExchange exchange) {

                try {

                    String response =
                            "<html><body>"
                            + "<h2>Addition Web Service</h2>"
                            + "<form action='/add' method='get'>"
                            + "Enter A: <input type='text' name='a'><br><br>"
                            + "Enter B: <input type='text' name='b'><br><br>"
                            + "<input type='submit' value='Add'>"
                            + "</form>"
                            + "</body></html>";

                    exchange.sendResponseHeaders(200, response.length());

                    OutputStream os = exchange.getResponseBody();

                    os.write(response.getBytes());

                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Add Operation
        server.createContext("/add", new HttpHandler() {

            public void handle(HttpExchange exchange) {

                try {

                    String query = exchange.getRequestURI().getQuery();

                    int a = 0;
                    int b = 0;

                    if (query != null) {

                        String[] params = query.split("&");

                        a = Integer.parseInt(params[0].split("=")[1]);

                        b = Integer.parseInt(params[1].split("=")[1]);
                    }

                    int result = a + b;

                    String response =
                            "<html><body>"
                            + "<h2>Result = " + result + "</h2>"
                            + "<a href='/'>Go Back</a>"
                            + "</body></html>";

                    exchange.sendResponseHeaders(200, response.length());

                    OutputStream os = exchange.getResponseBody();

                    os.write(response.getBytes());

                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        server.start();

        System.out.println("Server running at http://localhost:8000");
    }
}