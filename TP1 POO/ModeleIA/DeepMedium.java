package ModeleIA;

/**
 * IA équilibrée : réponses détaillées, coût intermédiaire.
 */
public final class DeepMedium extends ModeleIA {

    public DeepMedium() {
        super("DeepMedium", 30, "Équilibrée");
        setFacturation(0.02);
    }

    @Override
    public String genererReponse(String message) {
        return "Voici une réponse équilibrée à \"" + message + "\" : "
                + "j'ai analysé ta demande et je te propose une réponse claire et structurée.";
    }
}
