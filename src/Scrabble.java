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
        scrabble.mostrarTauler();
        Punt centre = scrabble.centreTauler();
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
}
