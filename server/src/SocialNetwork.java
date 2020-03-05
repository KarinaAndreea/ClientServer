import java.io.Serializable;
import java.util.*;

public class SocialNetwork implements Serializable {
    private final Set<Person> users = new HashSet<>();
    private final Map<String, Person> usersMap = new HashMap<>();
    private Person user;


    public SocialNetwork(){}

    /**
     * Creaza un utilizator cu numele specificat si il adauga in retea. Returneaza utilizatorul nou creat sau
     * null dacă un utilizator cu acest nume există deja în rețea.
     *
     * @param username este numele utilizatorului
     * @return noul utilizator creat sau null
     */
    public  Person register(String username){
        if (getUser(username) != null) {
            System.out.println("Username invalid!");
            return  null;
        }
        else {
            user = new Person(username);
            System.out.println("User-ul " + username + " s-a inregistrat");
            users.add(user);
            usersMap.put(username, user);
            return user;
        }
    }

    /**
     * Cauta si returneaza utilizatorul cu numele specificat. Daca utilizatorul nu este inregistrat in retea
     * returneaza null
     *
     * @param username numele utilizatorului
     * @return utilizatorul cu numele specificat sau null
     */
    public Person getUser(String username) {
        return usersMap.get(username);
    }

    /**
     * Înregistreaza o prietenie între doi utilizatori din rețea
     *
     * @param user1 este utilizatorul care a trimit cererea
     * @param user2 este utilizatorul care va primi cererea
     */
    public String addFriendship(Person user1, Person user2) {
        if(user1 == null) {
            System.out.println("User neautentificat!");
            return "Nu esti autentificat";
        }
        if (!users.contains(user1) || !users.contains(user2))
        {
            System.out.println("Unul dintre cei doi utilizatori nu aparține rețelei");
            return "Unul dintre utilizatori nu e autentificat";
        }
        else{
            user1.addFriend(user2);
            user2.addFriend(user1);
            return "Prieten adaugat";
        }
    }

    public String sendFriendsMessage(Person user, String message){
        if(user.getFriends() == null)
            return "Utilizatorul nu are prieteni";
        else {
            for (Person p : user.getFriends())
                p.addMessages(message);
            return "Ti-am trimis mesajul";
        }
    }

    public Set<Person> getUsers() {
        return users;
    }

}
