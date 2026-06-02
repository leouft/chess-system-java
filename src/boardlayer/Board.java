package boardlayer;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return  columns;
    }

    public Piece piece(int row, int columns) {
        return pieces[row][columns];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if (pieces[position.getRow()][position.getRow()] == null) {
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getRow()] = null;
        return aux;
    }

    public boolean positionExists(Position position) {
        return (position.getRow() > 0 && position.getRow() <= rows && position.getColumn() > 0 && position.getColumn() <= columns);
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            return false;
        }
        return piece(position) != null;
    }
}
