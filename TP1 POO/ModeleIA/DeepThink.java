package ModeleIA;

public final class DeepThink extends ModeleIA {

    public DeepThink() {
        super("DeepThink", 100, "Approfondie");
        setFacturation(0.05);
    }

    @Override
    public String genererReponse(String message) {
        return "Après réflexion approfondie sur \"" + message + "\" : "
                + "j'ai exploré plusieurs pistes, comparé les options et voici ma réponse détaillée.";
    }
}
