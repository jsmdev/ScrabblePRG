// José Sancho Monrabal
// 1 DAW - PRG
// Variant del joc de l'Scrabble, on només participarà 1 jugador

package model;

public enum TipusCasella {
    EN_BLANC('~', 0),
    DOBLE_LLETRA('l', 2),
    TRIPLE_LLETRA('L', 3),
    DOBLE_PARAULA('p', 2),
    TRIPLE_PARAULA('P', 3);

    private final char tipus;
    private final int multiplicador;

    TipusCasella(char simbol, int multiplicador) {
        this.tipus = simbol;
        this.multiplicador = multiplicador;
    }

    public char getTipus() {
        return tipus;
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public static TipusCasella fromChar(char c) {
        for (TipusCasella tipus : values()) {
            if (tipus.tipus == c) {
                return tipus;
            }
        }
        throw new IllegalArgumentException("Caràcter no vàlid: " + c);
    }
}
