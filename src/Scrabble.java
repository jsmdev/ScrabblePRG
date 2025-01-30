import java.util.Scanner;

public class Scrabble {
    public enum TipusCasella {
        EN_BLANC(' ', 0),
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

    private TipusCasella[][] mPunts;
    private char[][] mFitxes;
    private int qFilesTauler;
    private int qColumnesTauler;

    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble();
        scrabble.crearTauler();
        scrabble.inicialitzarTauler();
        scrabble.mostrarTauler();
    }

    public void crearTauler() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Ingresse la quantitat de files (imparell): ");
            qFilesTauler = scanner.nextInt();
        } while (qFilesTauler % 2 == 0);

        do {
            System.out.print("Ingresse la quantitat de columnes (imparell): ");
            qColumnesTauler = scanner.nextInt();
        } while (qColumnesTauler % 2 == 0);

        mPunts = new TipusCasella[qFilesTauler][qColumnesTauler];
        mFitxes = new char[qFilesTauler][qColumnesTauler];

        System.out.println("Tauler creat amb " + qFilesTauler + " files i " + qColumnesTauler + " columnes.");
    }

    public void inicialitzarTauler() {
        for (int i = 0; i < qFilesTauler; i++) {
            for (int j = 0; j < qColumnesTauler; j++) {
                mPunts[i][j] = TipusCasella.EN_BLANC;
            }
        }
        System.out.println("Tauler inicialitzat correctament.");
    }

    public void mostrarTauler() {
        for (int i = 0; i < qFilesTauler; i++) {
            for (int j = 0; j < qColumnesTauler; j++) {
                System.out.print(mPunts[i][j].getTipus() + " ");
            }
            System.out.println();
        }
    }
}
