package Fun.Chess;

import java.util.*;

public class Player {
    private int type;
    public Player(int type){
        this.type=type;
    }
    public String[] getMove(ChessBoard board, char team){
        String[] move=new String[2];
        if (type==1){
            Scanner s=new Scanner(System.in);
            System.out.print("Enter move (e.g., g1 f3): ");
            move[0] = s.next();
            move[1] = s.next();
        }else if (type==2){
            List<List<Integer>> allMoves = board.getAllLegalMoves(board.copyBoard(), team);
            for (List<Integer> m : allMoves) {
                //board.makeMove(m.get(0), m.get(1), team, new Scanner(System.in));
                System.out.println("" + (char)('a' + m.get(1)) + (8 - m.get(0)) + " to " + (char)('a' + m.get(3)) + (8 - m.get(2)));
            }
            if (allMoves.size() > 0) {
                Random rand = new Random();
                List<Integer> moveIndices = allMoves.get(rand.nextInt(allMoves.size()));
                move[0] = "" + (char)('a' + moveIndices.get(1)) + (8 - moveIndices.get(0));
                move[1] = "" + (char)('a' + moveIndices.get(3)) + (8 - moveIndices.get(2));
            }
        }
        return move;
    }
}
