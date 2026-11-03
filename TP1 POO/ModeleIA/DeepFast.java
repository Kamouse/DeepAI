package ModeleIA;

/**
 * IA rapide : réponses courtes, peu coûteuses.
 */
public final class DeepFast extends ModeleIA {

    public DeepFast() {
        super("DeepFast", 10, "Rapide");
        setFacturation(0.01);
    }

    @Override
    public String genererReponse(String message) {
        return "Réponse rapide : j'ai bien reçu \"" + message + "\".";
    }
}
