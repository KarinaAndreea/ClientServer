
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocialNetworkClient {
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;
    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;

    public static void main(String[] args) throws IOException {
        SocialNetworkClient client = new SocialNetworkClient();
        socket = new Socket(SERVER_ADDRESS, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (true) {
            String request = client.readFromKeyboard();
            if (request.equalsIgnoreCase("stop")) {
                try {
                    client.sendRequestToServer(request);
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                client.sendRequestToServer(request);
            }
        }

    }

    //Implement the sendRequestToServer method
    void sendRequestToServer(String request) {
        try {

            //System.out.println("Te-ai conenctat");
            // Trimitem o cerere la server
            out.println(request);
            out.flush();
            String response = in.readLine();

            System.out.println(response);
            System.out.println("\n");
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
