import java.util.Scanner;

public class Scrabble {
     public enum TipusCasella {
        DOBLE_LLETRA('l', 2),
        TRIPLE_LLETRA('L', 3),
        DOBLE_PARAULA('p', 2),
        TRIPLE_PARULA('P', 3);

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TipusCasella[][] mPunts;
        char[][] mFitxes;
        int qFilesTauler;
        int qColumnesTauler;

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

        mPunts[0][0] = TipusCasella.DOBLE_LLETRA;

        System.out.println("Files: " + mPunts.length);
        System.out.println("Columnes: " + mPunts[0].length);
        System.out.println("Files: " + mFitxes.length);
        System.out.println("Columnes: " + mFitxes[0].length);
        System.out.println("Primera casella: " + mPunts[0][0].getTipus());
    }
}
