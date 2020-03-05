
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class SocialNetworkServer {
    private static final int PORT = 8100;
    private ServerSocket serverSocket =null;
    private boolean running = false;
    //SocialNetwork socialNetwork = new SocialNetwork();

    public static void main(String[] args)  {
        SocialNetworkServer  server = new SocialNetworkServer();
        server.init();
        //new ClientThread(socket).start();
        server.waitForClients(); //handle the exceptions!
    }

    // Implement the init() method: create the serverSocket and set running to true
    private void init() {
        try {
            serverSocket = new ServerSocket( PORT );
        } catch (IOException e) {
            e.printStackTrace();
        }
        running = true;
    }

    public void stop() {
        try {
            this.running = false;
            serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    // Implement the waitForClients() method: while running is true,
    // create a new socket for every incoming client and start a ClientThread to execute its request.

    public void waitForClients() {
        System.out.println(" Asteptam un client ...");
        try {
            serverSocket.setSoTimeout((int) TimeUnit.SECONDS.toMillis(1000));
            while (running == true) {
                Socket socket = serverSocket.accept();
                // Executam solicitarea clientului intr -un fir de executie
                new ClientThread(socket,this).start();

                //System.out.println(" S-a conectat un client ...");
            }
        } catch (IOException e) {
            System.err.println("Eroare " + e);
        }
        finally {
            System.out.println("Serverul s-a inchis");
        }
    }

}
