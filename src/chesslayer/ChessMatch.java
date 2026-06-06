package chesslayer;

import boardlayer.Board;
import boardlayer.Position;
import chesslayer.pieces.King;
import chesslayer.pieces.Pawn;
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

    public void newPieceOnBoard(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnBoard.add(piece);
    }

    public void initialSetup(){
        // Brancas
        newPieceOnBoard('e', 1, new King(board, Color.WHITE, this)); // Rei
        newPieceOnBoard('a', 1, new Rook(board, Color.WHITE)); // Torre 1
        newPieceOnBoard('h', 1, new Rook(board, Color.WHITE)); // Torre 2
        /*
        newPieceOnBoard(7, 1, new Knight(board, Color.WHITE)); // Cavalo 1
        newPieceOnBoard(7, 6, new Knight(board, Color.WHITE)); // Cavalo 2
        newPieceOnBoard(7, 2, new Bishop(board, Color.WHITE)); // Bispo 1
        newPieceOnBoard(7, 5, new Bishop(board, Color.WHITE)); // Bispo 2
        newPieceOnBoard(7, 3, new Queen(board, Color.WHITE)); // Rainha 1
        */
        newPieceOnBoard('a', 2, new Pawn(board, Color.WHITE, this));
        newPieceOnBoard('b', 2, new Pawn(board, Color.WHITE, this));
        newPieceOnBoard('c', 2, new Pawn(board, Color.WHITE, this));
        newPieceOnBoard('d', 2, new Pawn(board, Color.WHITE, this));
        newPieceOnBoard('e', 2, new Pawn(board, Color.WHITE, this));
        newPieceOnBoard('f', 2, new Pawn(board, Color.WHITE, this));
        newPieceOnBoard('g', 2, new Pawn(board, Color.WHITE, this));
        newPieceOnBoard('h', 2, new Pawn(board, Color.WHITE, this));

        // Pretas
        newPieceOnBoard('e', 8, new King(board, Color.BLACK, this)); // Rei
        newPieceOnBoard('a', 8, new Rook(board, Color.BLACK)); // Torre 1
        newPieceOnBoard('h', 8, new Rook(board, Color.BLACK)); // Torre 2
        newPieceOnBoard('a', 7, new Pawn(board, Color.BLACK, this));
        newPieceOnBoard('b', 7, new Pawn(board, Color.BLACK, this));
        newPieceOnBoard('c', 7, new Pawn(board, Color.BLACK, this));
        newPieceOnBoard('d', 7, new Pawn(board, Color.BLACK, this));
        newPieceOnBoard('e', 7, new Pawn(board, Color.BLACK, this));
        newPieceOnBoard('f', 7, new Pawn(board, Color.BLACK, this));
        newPieceOnBoard('g', 7, new Pawn(board, Color.BLACK, this));
        newPieceOnBoard('h', 7, new Pawn(board, Color.BLACK, this));

    }

}