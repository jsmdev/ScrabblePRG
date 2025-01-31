// José Sancho Monrabal
// 1 DAW - PRG
// Variant del joc de l'Scrabble, on només participarà 1 jugador

import model.TipusCasella;
import model.Punt;

import java.util.Scanner;

public class Scrabble {
    private char[][] mPunts;
    private char[][] mFitxes;
    private int qFilesTauler;
    private int qColumnesTauler;

    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble();
        scrabble.crearTauler();
        scrabble.inicialitzarTauler();
        scrabble.inicialitzarAspesAmbParaulesDobleTant();
        scrabble.inicialitzarCantonsAmbParaulesTripleTant();
        scrabble.inicialitzarMeitatsAmbParaulesTripleTant();
        for (int i = 0; i < 5; i++) {
            scrabble.inicialitzarLletresDobleTant();
        }
        scrabble.inicialitzarLletresTripleTant();
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

        mPunts = new char[qFilesTauler][qColumnesTauler];
        mFitxes = new char[qFilesTauler][qColumnesTauler];

        System.out.println("Tauler creat amb " + qFilesTauler + " files i " + qColumnesTauler + " columnes.");
    }

    public void inicialitzarTauler() {
        for (int i = 0; i < qFilesTauler; i++) {
            for (int j = 0; j < qColumnesTauler; j++) {
                mPunts[i][j] = TipusCasella.EN_BLANC.getTipus();
            }
        }
        System.out.println("Tauler inicialitzat correctament.");
    }

    public Punt centreTauler() {
        int centreFila = qFilesTauler / 2;
        int centreColumna = qColumnesTauler / 2;
        TipusCasella tipus = TipusCasella.fromChar(mPunts[centreFila][centreColumna]);
        Punt centre = new Punt(centreFila, centreColumna, tipus);
        System.out.println("Centre del tauler: " + centre.descripcio());
        return centre;
    }

    public void inicialitzarAspesAmbParaulesDobleTant() {
        Punt centre = centreTauler();
        int centreFila = centre.getFila();
        int centreColumna = centre.getColumna();

        // Pose la 'p' al centre
        mPunts[centreFila][centreColumna] = TipusCasella.DOBLE_PARAULA.getTipus();

        // Bucle per a possar la 'p' a totes les aspes alhora
        for (int i = 1; i < Math.max(qFilesTauler, qColumnesTauler); i++) {
            // Aspa dreta-baix
            if (centreFila + i < qFilesTauler && centreColumna + i < qColumnesTauler) {
                mPunts[centreFila + i][centreColumna + i] = TipusCasella.DOBLE_PARAULA.getTipus();
            }
            // Aspa esquerra-dalt
            if (centreFila - i >= 0 && centreColumna - i >= 0) {
                mPunts[centreFila - i][centreColumna - i] = TipusCasella.DOBLE_PARAULA.getTipus();
            }
            // Aspa dreta-dalt
            if (centreFila - i >= 0 && centreColumna + i < qColumnesTauler) {
                mPunts[centreFila - i][centreColumna + i] = TipusCasella.DOBLE_PARAULA.getTipus();
            }
            // Aspa esquerra-baix
            if (centreFila + i < qFilesTauler && centreColumna - i >= 0) {
                mPunts[centreFila + i][centreColumna - i] = TipusCasella.DOBLE_PARAULA.getTipus();
            }
        }
    }

    public void inicialitzarCantonsAmbParaulesTripleTant() {
        // Posar 'P' als quatre cantons
        mPunts[0][0] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Superior esquerra
        mPunts[0][qColumnesTauler - 1] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Superior dreta
        mPunts[qFilesTauler - 1][0] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Inferior esquerra
        mPunts[qFilesTauler - 1][qColumnesTauler - 1] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Inferior dreta

        System.out.println("Quatre cantons del tauler inicialitzats amb 'P'.");
    }

    public void inicialitzarMeitatsAmbParaulesTripleTant() {
        // Posar 'P' a la meitat de cada costat
        mPunts[0][qColumnesTauler / 2] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Superior
        mPunts[qFilesTauler - 1][qColumnesTauler / 2] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Inferior
        mPunts[qFilesTauler / 2][0] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Esquerra
        mPunts[qFilesTauler / 2][qColumnesTauler - 1] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Dreta

        System.out.println("Quatre meitats de cada costat del tauler inicialitzats amb 'P'.");
    }

    public void inicialitzarLletresDobleTant() {
        int intents = Math.max(qFilesTauler, qColumnesTauler); // Límits d'intents per evitar bucles infinits
        while (intents > 0) {
            int numAleatoriFiles = (int)(Math.random() * qFilesTauler);
            int numAleatoriColumnes = (int)(Math.random() * qColumnesTauler);

            if (mPunts[numAleatoriFiles][numAleatoriColumnes] == TipusCasella.EN_BLANC.getTipus()) {
                System.out.println("Casella [" + numAleatoriFiles + "][" + numAleatoriColumnes + "] lliure.");

                // Marquem la casella i les seves simètriques
                marcarCasellesSimetriquesDobleLletra(numAleatoriFiles, numAleatoriColumnes);
                return; // Sortim després de col·locar les caselles
            }
            intents--;
        }
        System.out.println("No s'han trobat caselles lliures.");
    }

    public void marcarCasellesSimetriquesDobleLletra(int fila, int columna) {
        // Marquem la posició original lliure
        mPunts[fila][columna] = TipusCasella.DOBLE_LLETRA.getTipus();

        // Simetria vertical
        int simetriaVertical = qFilesTauler - 1 - fila;
        if (simetriaVertical >= 0 && simetriaVertical < qFilesTauler) {
            mPunts[simetriaVertical][columna] = TipusCasella.DOBLE_LLETRA.getTipus();
        }

        // Simetria horitzontal
        int simetriaHoritzontal = qColumnesTauler - 1 - columna;
        if (simetriaHoritzontal >= 0 && simetriaHoritzontal < qColumnesTauler) {
            mPunts[fila][simetriaHoritzontal] = TipusCasella.DOBLE_LLETRA.getTipus();
        }

        // Simetria diagonal
        if (simetriaVertical >= 0 && simetriaVertical < qFilesTauler && simetriaHoritzontal >= 0 && simetriaHoritzontal < qColumnesTauler) {
            mPunts[simetriaVertical][simetriaHoritzontal] = TipusCasella.DOBLE_LLETRA.getTipus();
        }

        System.out.println("Caselles simètriques marcades a partir de [" + fila + "][" + columna + "].");
    }

    public void inicialitzarLletresTripleTant() {
        // Generem una posició inicial aleatòria dins de les tres primeres posicions
        int limit = 3;
        int filaInicial = (int)(Math.random() * limit);
        int columnaInicial = (int)(Math.random() * limit);

        System.out.println("Posició inicial de 'L': [" + filaInicial + "][" + columnaInicial + "]");

        // Recorrem el tauler en intervals de 4 files i 4 columnes
        for (int fila = filaInicial; fila < qFilesTauler; fila += 4) {
            for (int columna = columnaInicial; columna < qColumnesTauler; columna += 4) {
                if (mPunts[fila][columna] == TipusCasella.EN_BLANC.getTipus()) {
                    mPunts[fila][columna] = TipusCasella.TRIPLE_LLETRA.getTipus();
                    System.out.println("'L' posada a [" + fila + "][" + columna + "]");
                }
            }
        }
    }

    public void mostrarTauler() {
        System.out.println("Tauler Scrabble:");
        // Mostrar la numeració de les columnes en la part superior
        System.out.print("   "); // Espai inicial per a l'alineació
        for (int j = 1; j <= qColumnesTauler; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();

        for (int i = 0; i < qFilesTauler; i++) {
            // Mostrar la numeració de les files a l'esquerra
            System.out.printf("%2d ", i + 1);

            // Mostrar el contingut del tauler
            for (int j = 0; j < qColumnesTauler; j++) {
                System.out.print(mPunts[i][j] + "  ");
            }

            // Mostrar la numeració de les files a la dreta
            System.out.printf("%2d", i + 1);
            System.out.println();
        }

        // Mostrar la numeració de les columnes en la part inferior
        System.out.print("   "); // Espai inicial per a l'alineació
        for (int j = 1; j <= qColumnesTauler; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
    }
}
