package chesslayer.pieces;

import boardlayer.Board;
import boardlayer.Position;
import chesslayer.ChessPiece;
import chesslayer.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    private boolean moveAlowed(Position pos) {
        ChessPiece piece = (ChessPiece)getBoard().piece(pos);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] aux = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0, 0);

        pos.setValues(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        pos.setValues(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        pos.setValues(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        pos.setValues(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        pos.setValues(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        pos.setValues(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        pos.setValues(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        pos.setValues(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        return aux;
    }

    @Override
    public String toString() {
        return "N";
    }
}
