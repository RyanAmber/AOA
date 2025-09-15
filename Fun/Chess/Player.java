package Fun.Chess;

import java.util.*;

public class Player {
    private int type;
    public Player(int type){
        this.type=type;
    }
    public String[] getMove(ChessBoard board){
        String[] move=new String[2];
        if (type==1){
            Scanner s=new Scanner(System.in);
            System.out.print("Enter move (e.g., g1 f3): ");
            move[0] = s.next();
            move[1] = s.next();
        }else if (type==2){
            
        }
        return move;
    }
}
