
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Person implements Serializable {
    private final  String username;
    private Set<Person> friendList;
    private Set<String> messages;

    /**
     * Creaza un utilizator nou cu numele specificat.
     * @param username numele utilizatorul care va fi creat
     * @throws IllegalArgumentException dacă argumetul este nul
     */
    public Person(String username) {
        if (username == null)
            throw new IllegalArgumentException();
        this.username = username;
        this.friendList = new HashSet<>();
        this.messages = new HashSet<>();
    }
    public String getUsername() {
        return this.username;
    }

    /**
     * @return colecția de prieteni a acestui utilizator.
     */
    public Set<Person> getFriends() {
        return friendList;
    }

    /**
     * Adaugă un utilizator la lista de prieteni.
     *  @param user este utilizatorul care va fi adaugat in lista de prieteni
     */
    public void addFriend(Person user) {
        friendList.add(user);
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void addMessages(String message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", friendList=" + friendList +
                ", messages=" + messages +
                '}';
    }
}

