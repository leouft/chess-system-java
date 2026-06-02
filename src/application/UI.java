package application;

import chesslayer.ChessPiece;
import chesslayer.Color;

public class UI {
    public static final String RESET = "\u001B[0m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLACK = "\u001B[30m";

    public static final String BLUE_BACKGROUND = "\u001B[44m";


    public static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-" + RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE)
                System.out.print(WHITE + piece + RESET);
            else
                System.out.print(BLACK + piece + RESET);
        }
        System.out.print(" ");
    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8-i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }


}
