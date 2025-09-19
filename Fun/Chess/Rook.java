//package Fun.Chess;

public class Rook extends ChessPiece {
    public Rook(char color) { super(color); }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, ChessBoard boardObj) {
        ChessPiece[][] board = boardObj.board;
        if (fromRow != toRow && fromCol != toCol) return false;
        int stepRow = Integer.signum(toRow - fromRow);
        int stepCol = Integer.signum(toCol - fromCol);
        int r = fromRow + stepRow, c = fromCol + stepCol;
        while (r != toRow || c != toCol) {
            if (board[r][c] != null) return false;
            r += stepRow; c += stepCol;
        }
        return board[toRow][toCol] == null || board[toRow][toCol].getColor() != color;
    }

    @Override
    public String toString() { return color == 'w' ? "R" : "r"; }
}
