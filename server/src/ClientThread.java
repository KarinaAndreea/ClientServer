
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private Person client = null;
    private final SocialNetworkServer server;
    private SocialNetwork socialNetwork = new SocialNetwork();
    private boolean exit = false;
    public ClientThread(Socket socket, SocialNetworkServer  server) {
        this.socket=socket;
        this.server = server;
    }

    public void run() {
        try {
            while (!exit) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //client -> server stream
                String request = in.readLine();
                String response = execute(request);
                PrintWriter out = new PrintWriter(socket.getOutputStream()); //server -> client stream
                out.println(response);
                out.flush();
            }
        }catch (IOException e){
            System.out.printf("Communication error..." + e);
        }
    }


    private String execute(String request) throws IOException {
        System.out.println("Server received the request " + request );
        String[] parts = request.split(" ");
        switch ( parts[0] ) {
            case "register":
                socialNetwork.register(parts[1]);
                System.out.println(socialNetwork.getUsers());
                return "Te-ai inregistrat";
            case "login":
               /*Person person = server.getUsersNetwork().getUser(parts[1]);
               if(person == null) {
                   System.out.println("Username invalid");
               }else {*/
               if(parts[1] == null)
                   System.out.println("Comanda e de forma -login <username>-");
                else {
                   client = socialNetwork.getUser(parts[1]);
                   if(client == null)
                       return "Username invalid";
                   else
                       return "Te-ai logat ca " + client.getUsername();

               }
                break;
            case "friend":
                String mesaj;
                for (int i = 1, n = parts.length; i < n; i++) {
                    mesaj = socialNetwork.addFriendship(client,//aici trebuie usernamul clientului curent
                            socialNetwork.getUser(parts[i]));
                    System.out.println(mesaj);
                }
                    break;

            case "read":
                String yourMessages = new String();
                int messageIndex=1;
                for(String msg : client.getMessages()){
                    yourMessages += "Mesajul " + messageIndex + ": " +msg + " ";
                    messageIndex++;
                }
                return yourMessages;
            case"send":
                String message = new String();
                for (int i = 1, n = parts.length; i < n; i++)
                    message +=parts[i] + " ";
                System.out.println(message);
                return socialNetwork.sendFriendsMessage(client,message);
            case "stop":
                exit=true;
                server.stop();
                return "Stopped";
        }
        return "";
    }

}


