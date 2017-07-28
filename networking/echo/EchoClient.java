import java.io.*;
import java.net.*;
 
public class EchoClient {
    public static void main(String[] args) throws IOException {
        // Ensure two arguments (host name & port number) were passed
        if (args.length != 2) {
            System.err.println(
                "Please type: java EchoClient <host name> <port number>");
            System.exit(1);
        }
    
        // Assign the two arguments to variables
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            // 1 // Open a socket.
            Socket echoSocket = new Socket(hostName, portNumber);

            // 2 // 
            // Get socket's output stream and open a PrintWriter on it
            // NOTE: This allows the client to send data through the socket to the server
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            // Get socket's input stream and open a BufferedReader on it
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            
            // Get the data that will be written to the output stream
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            // 3 // Read from and write to the stream
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
