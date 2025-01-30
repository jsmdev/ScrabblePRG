import java.util.Scanner;

public class Scrabble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board;
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

        board = new char[qFilesTauler][qColumnesTauler];
        System.out.println("Files: " + board.length);
        System.out.println("Columnes: " + board[0].length);
    }
}
