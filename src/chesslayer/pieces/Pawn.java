package chesslayer.pieces;

import boardlayer.Board;
import boardlayer.Position;
import chesslayer.ChessMatch;
import chesslayer.ChessPiece;
import chesslayer.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] aux = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0, 0);

        if (getColor() == Color.WHITE) { // Brancas

            pos.setValues(position.getRow() - 1, position.getColumn());

            if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) // Movimento padrão para frente
                aux[pos.getRow()][pos.getColumn()] = true;

            pos.setValues(position.getRow() - 2, pos.getColumn());
            Position pos2 = new Position(position.getRow() - 1, position.getColumn());

            if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos) && !getBoard().thereIsAPiece(pos2) && getMoveCount() == 0) // Movimento de duas casas
                aux[pos.getRow()][pos.getColumn()] = true;


            pos.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Movimento de captura direita
                aux[pos.getRow()][pos.getColumn()] = true;

            pos.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Movimento de captura esquerda
                aux[pos.getRow()][pos.getColumn()] = true;

            // En passant
            if (position.getRow() == 3) {
                Position posLeft = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(posLeft) && isThereOpponentPiece(posLeft) && getBoard().piece(posLeft) == chessMatch.getEnPassantVulnerable())
                    aux[posLeft.getRow() - 1][posLeft.getColumn()] = true;

                Position posRight = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(posRight) && isThereOpponentPiece(posRight) && getBoard().piece(posRight) == chessMatch.getEnPassantVulnerable())
                    aux[posRight.getRow() - 1][posRight.getColumn()] = true;
            }
        }
        else { // Pretas

            pos.setValues(position.getRow() + 1, position.getColumn());

            if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) // Movimento padrão pra frente
                aux[pos.getRow()][pos.getColumn()] = true;

            pos.setValues(position.getRow() + 2, position.getColumn());
            Position pos2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos) && !getBoard().thereIsAPiece(pos2) && getMoveCount() == 0) // Movimento de duas casas
                aux[pos.getRow()][pos.getColumn()] = true;

            pos.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Movimento de captura direita
                aux[pos.getRow()][pos.getColumn()] = true;

            pos.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) // Movimento de captura esquerda
                aux[pos.getRow()][pos.getColumn()] = true;

            // En passant

            if (position.getRow() == 4) {
                Position posLeft = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(posLeft) && isThereOpponentPiece(posLeft) && getBoard().piece(posLeft) == chessMatch.getEnPassantVulnerable())
                    aux[posLeft.getRow() + 1][posLeft.getColumn()] = true;

                Position posRight = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(posRight) && isThereOpponentPiece(posRight) && getBoard().piece(posRight) == chessMatch.getEnPassantVulnerable())
                    aux[posRight.getRow() + 1][posRight.getColumn()] = true;
            }
        }
        return aux;
    }

    @Override
    public String toString() {
        return "P";
    }
}
