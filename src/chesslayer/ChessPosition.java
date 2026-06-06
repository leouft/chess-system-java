package chesslayer;

import boardlayer.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8)
            throw new ChessException("Invalid ChessPosition. Valid range: a1 to h8 [PRESS ENTER TO CONTINUE].");
        this.column = column;
        this.row = row;
    }

    public char getColumn(){
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition(){ // Converte posições a1, a2, b1... para 0, 0; 0,1; 1;0...
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position position) { // Converte posições 0,0; 0,1; 1;0... para a1, a2, b1...
        return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return ""+column+row;
    }
}
