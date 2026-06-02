package chesslayer;

import boardlayer.Board;
import boardlayer.Position;
import chesslayer.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    private Board board;

    private List<ChessPiece> piecesOnBoard = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
    }
    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public ChessPiece getPromoted() {
        return promoted;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] aux = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                aux[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return aux;
    }

    public void newPieceOnBoard(int row, int column, ChessPiece piece) {
        Position pos = new Position(row, column);
        board.placePiece(piece, pos);
        piecesOnBoard.add(piece);
    }

    public void initialSetup(){
        // Brancas
        newPieceOnBoard(7, 0, new Rook(board, Color.WHITE)); // Torre 1
        newPieceOnBoard(7, 7, new Rook(board, Color.WHITE)); // Torre 2
    }

}