package chesslayer.pieces;

import chesslayer.ChessPiece;

public class Rock extends ChessPiece {

    public Rock(Board board, Color color){
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] aux = new boolean[getBoard().getRows()][getBoard().getColuns()];

        Position pos = new Position(0, 0);

        //movimentos da torre
        //cima
        pos.setValue(position.getRow() - 1, position.getColuns()){
            while(getBoard().positionExist(pos) && !getBoard().thereIsAPiece(pos)){
                aux[pos.getRow()][pos.getColumn()] = true;
                pos.setRow(pos.getRow() - 1);
            }
        }

        //baixo
        pos.setValue(position.getRow() + 1, position.getColuns()){
            while(getBoard().positionExist(pos) && !getBoard().thereIsAPiece(pos)){
                aux[pos.getRow()][pos.getColumn()] = true;
                pos.setRow(pos.getRow() + 1);
            }
        }

        //direita
        pos.setValue(position.getRow(), position.getColuns() + 1){
            while(getBoard().positionExist(pos) && !getBoard().thereIsAPiece(pos)){
                aux[pos.getRow()][pos.getColumn()] = true;
                pos.setRow(pos.getColuns() + 1);
            }
        }

        //esquerda
        pos.setValue(position.getRow(), position.getColuns() - 1){
            while(getBoard().positionExist(pos) && !getBoard().thereIsAPiece(pos)){
                aux[pos.getRow()][pos.getColumn()] = true;
                pos.setRow(pos.getColuns() - 1);
            }
        }

    }

}
