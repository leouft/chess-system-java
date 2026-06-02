package chesslayer.pieces;

import boardlayer.Board;
import boardlayer.Position;
import chesslayer.ChessMatch;
import chesslayer.ChessPiece;
import chesslayer.Color;

public class King extends ChessPiece {
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean moveAlowed(Position position) {
        ChessPiece piece = (ChessPiece)getBoard().piece(position);
        return piece == null || piece.getColor() != getColor(); // Se não tiver peça, ou se for uma peça rival, ele pode se mexer
    }

    private boolean testCastlingForRook(Position position) {
        ChessPiece temp = (ChessPiece)getBoard().piece(position);
        return (temp instanceof Rook && temp.getMoveCount() == 0 && temp.getColor() == getColor()); // Verifica se é ua torre, não se mexeu e é da mesma cor
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] aux = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0, 0);

        // Cima
        pos.setValues(position.getRow() - 1, pos.getColumn());
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Baixo
        pos.setValues(position.getRow() + 1, pos.getColumn());
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Esquerda
        pos.setValues(position.getRow(), pos.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Direita
        pos.setValues(position.getRow(), pos.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal sup esquerda
        pos.setValues(position.getRow() - 1, pos.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal sup direita
        pos.setValues(position.getRow() - 1, pos.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal inf esquerda
        pos.setValues(position.getRow() + 1, pos.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal inf direita
        pos.setValues(position.getRow() + 1, pos.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Roque
        if (getMoveCount() == 0) {
            // Roque pra esquerda
            boolean wayBlocked = false;
            for (int i = 3; i > 0; i--) {
                pos.setValues(7, i);
                if (getBoard().thereIsAPiece(pos)) {
                    wayBlocked = true;
                    break;
                }
            }
            if (!wayBlocked) {
                pos.setValues(7, 0);
                if (testCastlingForRook(pos))
                    aux[pos.getRow()][pos.getColumn()-2] = true;
            }

            // Roque pra direita
            wayBlocked = false;
            for (int i = 5; i < 7; i++) {
                pos.setValues(7, i);
                if (getBoard().thereIsAPiece(pos)) {
                    wayBlocked = true;
                    break;
                }
            }

            if (!wayBlocked) {
                pos.setValues(7,7);
                if (testCastlingForRook(pos))
                    aux[pos.getRow()][pos.getColumn() + 2] = true;
            }
        }
        return aux;
    }
}
