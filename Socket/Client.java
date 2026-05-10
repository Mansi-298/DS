package DS_Assignment1;

import java.io.*;
import java.net.*;

public class Client {

    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream out = null;

    public Client(String address, int port) {

        try {
            // Connect to server
            socket = new Socket(address, port);
            System.out.println("Connected to server");

            // Input from keyboard
            input = new BufferedReader(
                    new InputStreamReader(System.in));

            // Output to server
            out = new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException u) {
            System.out.println(u);
            return;
        } catch (IOException i) {
            System.out.println(i);
            return;
        }

        String line = "";

        // Send messages
        while (!line.equals("Over")) {
            try {
                line = input.readLine();   // FIXED
                out.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // Close resources
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        new Client("127.0.0.1", 3300);
    }
}