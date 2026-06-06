package chesslayer.pieces;

import boardlayer.Board;
import boardlayer.Position;
import chesslayer.ChessPiece;
import chesslayer.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] aux = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0, 0);

        // Movimentos

        // Cima-Direita
        pos.setValues(position.getRow() - 1, position.getColumn() + 1);
        while(getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() - 1);
            pos.setColumn(pos.getColumn() +1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Captura
            aux[pos.getRow()][pos.getColumn()] = true;

        // Cima-Esquerda
        pos.setValues(position.getRow() - 1, position.getColumn() - 1);
        while(getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() - 1);
            pos.setColumn(pos.getColumn() - 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Captura
            aux[pos.getRow()][pos.getColumn()] = true;

        // Baixo-Direita
        pos.setValues(position.getRow() + 1, position.getColumn() + 1);
        while(getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() + 1);
            pos.setColumn(pos.getColumn() + 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Captura
            aux[pos.getRow()][pos.getColumn()] = true;

        // Baixo-Esquerda
        pos.setValues(position.getRow(), position.getColumn() - 1);
        while(getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)){
            aux[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() + 1);
            pos.setColumn(pos.getColumn() - 1);
        }
        if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Captura
            aux[pos.getRow()][pos.getColumn()] = true;

        return aux;
    }
}