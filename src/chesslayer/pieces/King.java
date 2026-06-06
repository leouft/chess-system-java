package chesslayer.pieces;

import boardlayer.Board;
import boardlayer.Piece;
import boardlayer.Position;
import chesslayer.ChessMatch;
import chesslayer.ChessPiece;
import chesslayer.Color;

import java.util.List;
import java.util.stream.Collectors;

public class King extends ChessPiece {
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean moveAlowed(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return (piece == null || piece.getColor() != getColor()); // Se não tiver peça, ou se for uma peça rival, ele pode se mexer
    }

    private boolean testCastlingForRook(Position position) {
        ChessPiece temp = (ChessPiece) getBoard().piece(position);
        return (temp instanceof Rook && temp.getMoveCount() == 0 && temp.getColor() == getColor()); // Verifica se é ua torre, não se mexeu e é da mesma cor
    }

    private boolean underAttack(Position pos, Color color) {
        List<Piece> enemiesPieces = chessMatch.getPiecesOnBoard().stream().filter(x -> ((ChessPiece)x).getColor() != (color)).collect(Collectors.toList()); // Filtra apenas as peças inimigas
        for (Piece piece : enemiesPieces) {
            boolean[][] aux = piece.possibleMoves();
            if (aux[pos.getRow()][pos.getColumn()])
                return true;
        }
        return false;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] aux = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position pos = new Position(0, 0);

        // Cima
        pos.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Baixo
        pos.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Esquerda
        pos.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Direita
        pos.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal sup esquerda
        pos.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal sup direita
        pos.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal inf esquerda
        pos.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        // Diagonal inf direita
        pos.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(pos) && moveAlowed(pos))
            aux[pos.getRow()][pos.getColumn()] = true;

        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // Roque pra direita
            Position posRook = new Position(position.getRow(), position.getColumn() + 3);
            if (testCastlingForRook(posRook)) {
                Position pos1 = new Position(position.getRow(), position.getColumn() + 1);
                Position pos2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(pos1) == null && getBoard().piece(pos2) == null && !underAttack(pos1, getColor()) && !underAttack(pos2, getColor()))
                    aux[position.getRow()][position.getColumn() + 2] = true;
            }

            Position posRook2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testCastlingForRook(posRook2)) {
                Position pos1 = new Position(position.getRow(), position.getColumn() - 1);
                Position pos2 = new Position(position.getRow(), position.getColumn() - 2);
                Position pos3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(pos1) == null && getBoard().piece(pos2) == null && getBoard().piece(pos3) == null && !underAttack(pos1, getColor()) && !underAttack(pos2, getColor()))
                    aux[position.getRow()][position.getColumn() - 2] = true;
            }
        }

        return aux;
    }

    @Override
    public String toString() {
        return "K";
    }
}