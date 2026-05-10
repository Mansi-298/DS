package DS_Assignment1;

import java.net.*;
import java.io.*;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(int port) {
        try {
            // Start server
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client...");

            // Accept client
            socket = server.accept();
            System.out.println("Client accepted");

            // Input stream
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            // Read messages
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println("Client: " + line);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }

            System.out.println("Closing connection");

            // Close resources
            socket.close();
            in.close();
            server.close();

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        new Server(3300);
    }
}