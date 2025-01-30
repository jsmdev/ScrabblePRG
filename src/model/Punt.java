package model;

public class Punt {
    private int fila;
    private int columna;
    private TipusCasella tipusCasella;

    // Constructor
    public Punt(int fila, int columna, TipusCasella tipusCasella) {
        this.fila = fila;
        this.columna = columna;
        this.tipusCasella = tipusCasella;
    }

    // Mètodes d'accés
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public TipusCasella getTipusCasella() {
        return tipusCasella;
    }

    public void setTipusCasella(TipusCasella tipusCasella) {
        this.tipusCasella = tipusCasella;
    }

    // Mètode per obtenir una descripció del punt
    public String descripcio() {
        return "Posició: (" + fila + ", " + columna + "), Tipus de casella: " + tipusCasella;
    }

    @Override
    public String toString() {
        return descripcio();
    }
}
