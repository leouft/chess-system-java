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
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
        }
    }
}
