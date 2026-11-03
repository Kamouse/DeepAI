
import Login.LoginBox;
import Login.LoginValidation;
import Login.RegisterUser;
import ModeleIA.DeepFast;
import ModeleIA.DeepMedium;
import ModeleIA.DeepThink;
import ModeleIA.ModeleIA;
import User.Conversation;
import User.User;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Création du système d'inscription
        RegisterUser register = new RegisterUser();

        // Inscription
        boolean registered = register.register(
                "Nicolas",
                "nicolas@gmail.com",
                "password123",
                50.0
        );

        System.out.println("Inscription : " + registered);

        // Création des identifiants de connexion
        LoginBox login = new LoginBox(
                "nicolas@gmail.com",
                "password123"
        );

        // Vérification des identifiants
        LoginValidation validation = new LoginValidation();

        boolean connected = validation.validate(
                login.getEmail(),
                login.getPassword(),
                register.getUsers()
        );

        System.out.println("Connexion : " + connected);

        // La conversation n'est accessible qu'une fois connecté
        if (!connected) {
            System.out.println("Connexion refusée : conversation inaccessible.");
            return;
        }

        // Récupération de l'utilisateur connecté
        User userConnecte = null;
        for (User u : register.getUsers()) {
            if (u.getEmail().equals(login.getEmail())) {
                userConnecte = u;
            }
        }

        // IA disponibles (le choix se fait au début de la conversation)
        List<ModeleIA> modeles = List.of(new DeepFast(), new DeepMedium(), new DeepThink());

        Scanner scanner = new Scanner(System.in);

        Conversation conversation = new Conversation(userConnecte, modeles, modeles.get(0));
        conversation.demarrer(scanner);
    }
}
