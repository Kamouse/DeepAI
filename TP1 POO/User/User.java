package User;

import java.util.List;

public class User {

    private String username;
    private String email;
    private String password;
    private Double solde;
    private final List<Conversation> conversations;

    public User(String username, String email, String password, Double solde, List<Conversation> conversations) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.solde = solde;
        this.conversations = conversations;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Double getSolde() {
        return solde;
    }

    public void crediterSolde(double montant) {
        this.solde += montant;
    }

    public void debiter(double montant) {
        this.solde -= montant;
    }

}
