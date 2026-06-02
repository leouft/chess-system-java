package application;

import chesslayer.ChessMatch;

public class Program {
    public static void main(String[] args) {
        ChessMatch match = new ChessMatch();
        match.initialSetup();
        UI.printBoard(match.getPieces());
    }
}
