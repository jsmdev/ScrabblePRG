// José Sancho Monrabal
// 1 DAW - PRG
// Variant del joc de l'Scrabble, on només participarà 1 jugador

import model.TipusCasella;
import model.Punt;

import java.awt.*;
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
        scrabble.inicialitzarAspes();
        scrabble.inicialitzarCantons();
        scrabble.inicialitzarMeitats();
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

    public void mostrarTauler() {
        for (int i = 0; i < qFilesTauler; i++) {
            for (int j = 0; j < qColumnesTauler; j++) {
                System.out.print(mPunts[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Punt centreTauler() {
        int centreFila = qFilesTauler / 2;
        int centreColumna = qColumnesTauler / 2;
        TipusCasella tipus = TipusCasella.fromChar(mPunts[centreFila][centreColumna]);
        Punt centre = new Punt(centreFila, centreColumna, tipus);
        System.out.println("Centre del tauler: " + centre.descripcio());
        return centre;
    }

    public void inicialitzarAspes() {
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

    public void inicialitzarCantons() {
        // Posar 'P' als quatre cantons
        mPunts[0][0] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Superior esquerra
        mPunts[0][qColumnesTauler - 1] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Superior dreta
        mPunts[qFilesTauler - 1][0] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Inferior esquerra
        mPunts[qFilesTauler - 1][qColumnesTauler - 1] = TipusCasella.TRIPLE_PARAULA.getTipus(); // Inferior dreta

        System.out.println("Quatre cantons del tauler inicialitzats amb 'P'");
    }

    public void inicialitzarMeitats() {
        // Posar 'P' a la meitat de cada costat
        mPunts[0][qColumnesTauler / 2] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Superior
        mPunts[qFilesTauler - 1][qColumnesTauler / 2] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Inferior
        mPunts[qFilesTauler / 2][0] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Esquerra
        mPunts[qFilesTauler / 2][qColumnesTauler - 1] = TipusCasella.TRIPLE_PARAULA.getTipus();  // Dreta

        System.out.println("Quatre meitats de cada costat del tauler inicialitzats amb 'P'");
    }
}
