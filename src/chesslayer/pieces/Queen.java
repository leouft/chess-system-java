package chesslayer.pieces;

import boardlayer.Board;
import boardlayer.Position;
import chesslayer.ChessPiece;
import chesslayer.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] aux = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0, 0);

        // Pra cima
        pos.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() - 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        // Baixo
        pos.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() + 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        // Esquerda
        pos.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setColumn(pos.getRow() - 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        // Direita
        pos.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setColumn(pos.getRow() + 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        // Diagonal sup esquerda
        pos.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setValues(pos.getRow() - 1, pos.getColumn() - 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        // Diagonal sup direita
        pos.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setValues(pos.getRow() - 1, pos.getColumn() + 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        // Diagonal inf direita
        pos.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setValues(pos.getRow() + 1, pos.getColumn() + 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        // Diagonal inf esquerda
        pos.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setValues(pos.getRow() + 1, pos.getColumn() - 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
            aux[pos.getRow()][pos.getColumn()] = true;
        }

        return aux;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
