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
            List<List<Integer>> allMoves = board.getAllLegalMoves(team);
            Map<List<Integer>,Integer> scores=new HashMap<List<Integer>,Integer>();
            for (List<Integer> m : allMoves) {
                int startrow=m.get(0);
                int startcol=m.get(1);
                int endrow=m.get(2);
                int endcol=m.get(3);
                ChessPiece[][] testboard=board.copyBoard();
                testboard[endrow][endcol] = testboard[startrow][startcol];
                testboard[startrow][startcol] = null;
                scores.put(m,score(testboard,team));
            }
            //System.out.println("All moves listed");
            int maxscore=Integer.MIN_VALUE;
            List<List<Integer>> bestMoves=new ArrayList<List<Integer>>();
            for (Map.Entry<List<Integer>, Integer> entry : scores.entrySet()) {
                if (entry.getValue()>maxscore){
                    maxscore=entry.getValue();
                    bestMoves.clear();
                    bestMoves.add(entry.getKey());
                }else if(entry.getValue()==maxscore){
                    bestMoves.add(entry.getKey());
                }
            }
            List<Integer> moveIndices = bestMoves.get(new Random().nextInt(bestMoves.size()));
            move[0] = "" + (char)('a' + moveIndices.get(1)) + (8 - moveIndices.get(0));
            move[1] = "" + (char)('a' + moveIndices.get(3)) + (8 - moveIndices.get(2));
        }else{
            List<List<Integer>> allMoves = board.getAllLegalMoves(team);
            if (allMoves.size() > 0) {
                Random rand = new Random();
                List<Integer> moveIndices = allMoves.get(rand.nextInt(allMoves.size()));
                move[0] = "" + (char)('a' + moveIndices.get(1)) + (8 - moveIndices.get(0));
                move[1] = "" + (char)('a' + moveIndices.get(3)) + (8 - moveIndices.get(2));
            }
        }
        return move;
    }
    public int score(ChessPiece[][] board, char team){
        ChessBoard b=new ChessBoard();
        b.setupBoard(board);
        int score=0;
        int pieceValues[]={0,1,3,3,5,9,1000};
        for (int r=0;r<8;r++){
            for (int c=0;c<8;c++){
                if (board[r][c]!=null){
                    int val=0;
                    switch (board[r][c].toString()){
                        case "P": val=pieceValues[1]; break;
                        case "N": val=pieceValues[2]; break;
                        case "B": val=pieceValues[3]; break;
                        case "R": val=pieceValues[4]; break;
                        case "Q": val=pieceValues[5]; break;
                        case "K": val=pieceValues[6]; break;
                    }
                    if (board[r][c].getColor()==team){
                        score+=val;
                    }else{
                        score-=val;
                    }
                }
            }
        }
        if (b.isInCheck(team=='w'?'b':'w')){
            score+=50;
        }
        if (b.isInCheckmate(team=='w'?'b':'w')){
            score+=10000;
        }
        return score;
    }
}
