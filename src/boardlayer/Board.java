package boardlayer;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new BoardException("Board need be at least 1 row and 1 column.");
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

    public Piece piece(int row, int column) {
        Position pos = new Position(row, column);
        if (!positionExists(pos))
            throw new BoardException("Invalid position.");
        return pieces[row][columns];
    }

    public Piece piece(Position position) {
        if (!positionExists(position))
            throw new BoardException("Invalid position.");
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("There's a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if (!positionExists(position))
            throw new BoardException("Invalid position");
        if (piece(position) == null)
            return null;
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getRow()] = null;
        return aux;
    }

    public boolean positionExists(Position position) {
        return (position.getRow() >= 0 && position.getRow() < rows && position.getColumn() >= 0 && position.getColumn() < columns);
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Invalid position");
        }
        return piece(position) != null;
    }
}
