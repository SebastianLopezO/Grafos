package Bean;

public class Nodo {
    private Nodo Liga;
    private String Dato;

    public Nodo(Nodo Liga, String Dato) {
        this.Liga = Liga;
        this.Dato = Dato;
    }


    public Nodo() {
        Liga = null;
        Dato = "";
    }

    public Nodo getLiga() {
        return Liga;
    }

    public void setLiga(Nodo Liga) {
        this.Liga = Liga;
    }

    public String getDato() {
        return Dato;
    }

    public void setDato(String Dato) {
        this.Dato = Dato;
    }



}