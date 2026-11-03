package User;

import ModeleIA.ModeleIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Conversation en terminal entre un User connecté et une IA. L'IA peut être
 * changée à tout moment avec la commande /changer. Chaque message coûte
 * (tokenParMessage x facturation) débité du solde du User.
 */
public final class Conversation {

    private final User user;
    private final List<ModeleIA> modelesDisponibles;
    private ModeleIA modeleActuel;
    private int tokenUtilise;
    private final List<String> historique = new ArrayList<>();

    public Conversation(User user, List<ModeleIA> modelesDisponibles, ModeleIA modeleInitial) {
        if (user == null) {
            throw new IllegalStateException("Connexion requise pour démarrer une conversation.");
        }
        if (modelesDisponibles == null || modelesDisponibles.isEmpty()) {
            throw new IllegalArgumentException("Aucun modèle d'IA disponible.");
        }
        this.user = user;
        this.modelesDisponibles = modelesDisponibles;
        ModeleIA depart = modeleInitial;
        if (depart == null) {
            depart = modelesDisponibles.get(0);
        }
        this.modeleActuel = depart;
        user.getConversations().add(this);
    }

    // ---------- Getters ----------
    public String getNomModeleConv() {
        return modeleActuel.getNomModele();
    }

    public int getTokenUtilise() {
        return tokenUtilise;
    }

    public List<String> getHistorique() {
        return new ArrayList<>(historique);
    }

    // ---------- Logique ----------
    public void changerModele(ModeleIA nouveauModele) {
        this.modeleActuel = nouveauModele;
        historique.add("[Système] IA changée : " + nouveauModele.getNomModele());
    }

    /**
     * Envoie un message à l'IA courante Retourne null si le solde est
     * insuffisant.
     */
    public String envoyerMessage(String message) {
        double cout = modeleActuel.getTokenParMessage() * modeleActuel.getFacturation();

        if (user.getSolde() < cout) {
            return null;
        }

        historique.add("[" + user.getUsername() + "] " + message);

        String reponse = modeleActuel.genererReponse(message);

        user.debiter(cout);
        tokenUtilise += modeleActuel.getTokenParMessage();

        historique.add("[" + modeleActuel.getNomModele() + "] " + reponse);
        return reponse;
    }

    // ---------- Boucle terminal ----------
    public void demarrer(Scanner scanner) {
        System.out.println("=== Nouvelle conversation : choisis ton IA ===");
        while (!choisirModele(scanner)) {
            //  redemander tant que le choix n'est pas valide
        }
        historique.clear();

        System.out.println("=== Conversation avec " + modeleActuel.getNomModele() + " ===");
        afficherAide();

        while (true) {
            System.out.print("\nToi > ");
            String saisie = scanner.nextLine().trim();

            if (saisie.isEmpty()) {
                continue;
            }

            switch (saisie.toLowerCase()) {
                case "/quitter":
                    System.out.println("Conversation terminée. Tokens utilisés : " + tokenUtilise);
                    return;
                case "/changer":
                    choisirModele(scanner);
                    break;
                case "/historique":
                    historique.forEach(System.out::println);
                    break;
                case "/solde":
                    System.out.printf("Solde : %.2f%n", user.getSolde());
                    break;
                case "/aide":
                    afficherAide();
                    break;
                default:
                    String reponse = envoyerMessage(saisie);
                    if (reponse == null) {
                        System.out.println("Solde insuffisant pour " + modeleActuel.getNomModele()
                                + ". Change d'IA (/changer) ou crédite ton solde.");
                    } else {
                        System.out.println(modeleActuel.getNomModele() + " > " + reponse);
                    }
            }
        }
    }

    /**
     *
     * Affiche le menu des IA. Retourne true si un modele valide a été choisit
     */
    private boolean choisirModele(Scanner scanner) {
        System.out.println("Modèles disponibles :");
        for (int i = 0; i < modelesDisponibles.size(); i++) {
            ModeleIA m = modelesDisponibles.get(i);
            System.out.println("  " + (i + 1) + ") " + m.getNomModele()
                    + " (" + m.getTokenParMessage() + " tokens/message, " + m.getFacturation() + "/token)");
        }
        System.out.print("Choix > ");
        try {
            int choix = Integer.parseInt(scanner.nextLine().trim());
            if (choix >= 1 && choix <= modelesDisponibles.size()) {
                changerModele(modelesDisponibles.get(choix - 1));
                System.out.println("IA sélectionnée : " + modeleActuel.getNomModele());
                return true;
            }
            System.out.println("Choix invalide.");
        } catch (NumberFormatException e) {
            System.out.println("Entre un numéro.");
        }
        return false;
    }

    private void afficherAide() {
        System.out.println("Commandes : /changer, /historique, /solde, /aide, /quitter");
    }
}
