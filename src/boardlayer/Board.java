package boardlayer;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Piece piece(int row, int columns) {
        return pieces[row][columns];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }
}
