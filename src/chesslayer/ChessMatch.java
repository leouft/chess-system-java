package chesslayer;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public int getTurn() {
        return turn;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {

    }

    public ChessPiece performChessMove(
            ChessPosition sourcePosition,
            ChessPosition targetPosition) {

    }

    public ChessPiece replacePromotedPiece(String type) {

    }
}