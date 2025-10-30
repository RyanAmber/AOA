package Fun.Chess;
import java.util.*;

public class ChessBoardNode {
    private ChessBoard data;
    private List<ChessBoardNode> nextMoves;
    private char playerTurn;

    public ChessBoardNode(ChessBoard data, char playerTurn) {
        this.data = data;
        this.playerTurn = playerTurn;
        this.nextMoves = new ArrayList<>();
    }
    public void addNext(ChessBoardNode node) {
        nextMoves.add(node);
    }
    public void setDataToMaxDepth(int depth) {
        if(depth==0) {
            return;
        }
        List<List<Integer>> possibleMoves = data.getAllLegalMoves(playerTurn);
        for (List<Integer> move : possibleMoves) {
            ChessBoard newBoard = new ChessBoard();
            newBoard.setupBoard(data.getBoard());
            int startrow=move.get(0);
            int startcol=move.get(1);
            int endrow=move.get(2);
            int endcol=move.get(3);
            ChessPiece[][] tempBoard = newBoard.getBoard();
            tempBoard[endrow][endcol] = tempBoard[startrow][startcol];
            tempBoard[startrow][startcol] = null;
            char nextPlayer = (playerTurn == 'W') ? 'B' : 'W';
            newBoard.setupBoard(tempBoard);
            ChessBoardNode childNode = new ChessBoardNode(newBoard, nextPlayer);
            this.addNext(childNode);
            childNode.setDataToMaxDepth(depth - 1);
        }
    }

}
