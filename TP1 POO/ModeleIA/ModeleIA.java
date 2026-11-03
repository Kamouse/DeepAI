package ModeleIA;

public abstract class ModeleIA {

    protected String nomModele;
    protected int tokenParMessage;
    protected String typeReponse;
    protected double facturation;

    public ModeleIA(String nomModele, int tokenParMessage, String typeReponse) {
        this.nomModele = nomModele;
        this.tokenParMessage = tokenParMessage;
        this.typeReponse = typeReponse;
    }

    public void setFacturation(double facturation) {
        this.facturation = facturation;
    }

    public String getNomModele() {
        return nomModele;
    }

    public int getTokenParMessage() {
        return tokenParMessage;
    }

    public String getTypeReponse() {
        return typeReponse;
    }

    public double getFacturation() {
        return facturation;
    }

    public abstract String genererReponse(String message);
}
