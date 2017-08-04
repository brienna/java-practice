package multithreaded_chat;

import java.io.*;
import java.net.*;

public class ChatClient {
    private String hostName;
    private int portNumber;

    public ChatClient(String host, int port) {
        hostName = host;
        portNumber = port;

        start();
    }

    public void start() {
        try (
            // Open a socket
            Socket socket = new Socket(hostName, portNumber);
            // Open an output stream to the socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Open an input stream from the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Get the data that will be written to the output stream
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            // Read and write to the socket
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}