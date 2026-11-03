package Login;

import User.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterUser {

    private List<User> users = new ArrayList<>();

    public boolean register(String username, String email, String password, Double solde) {

        // Vérifier si l'email existe déjà
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }

        // Créer le nouvel utilisateur
        User newUser = new User(username, email, password, solde, new ArrayList<>());

        users.add(newUser);

        return true;
    }

    public List<User> getUsers() {
        return users;
    }
}
