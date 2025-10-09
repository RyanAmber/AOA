//package Fun.Chess;

import java.util.*;

public class ChessPlayer {
    private int type;
    public ChessPlayer(int type){
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
            Map<List<Integer>,Double> scores=new HashMap<List<Integer>,Double>();
            for (List<Integer> m : allMoves) {
                int startrow=m.get(0);
                int startcol=m.get(1);
                int endrow=m.get(2);
                int endcol=m.get(3);
                ChessPiece[][] testboard=board.copyBoard();
            int moves=board.halfmoveClock;
                if (testboard[endrow][endcol]!=null||testboard[startrow][startcol].toString().equals("P")){
                    moves=0;
                }else{
                    moves++;
                }
                testboard[endrow][endcol] = testboard[startrow][startcol];
                testboard[startrow][startcol] = null;
                scores.put(m,score(testboard,team,moves));
            }
            //System.out.println("All moves listed");
            double minimaxscore=team=='w'?Double.NEGATIVE_INFINITY:Double.POSITIVE_INFINITY;
            List<List<Integer>> bestMoves=new ArrayList<List<Integer>>();
            for (Map.Entry<List<Integer>, Double> entry : scores.entrySet()) {
                int fromRow = entry.getKey().get(0);
                int fromCol = entry.getKey().get(1);
                int toRow = entry.getKey().get(2);
                int toCol = entry.getKey().get(3);
                String fromNotation = "" + (char)('a' + fromCol) + (8 - fromRow);
                String toNotation = "" + (char)('a' + toCol) + (8 - toRow);
                System.out.println(minimaxscore + " " + entry.getValue() + " " + fromNotation + " " + toNotation);
                if (team=='w'?entry.getValue()>minimaxscore:entry.getValue()<minimaxscore){
                    minimaxscore=entry.getValue();
                    bestMoves.clear();
                    bestMoves.add(entry.getKey());
                }else if(entry.getValue()==minimaxscore){
                    bestMoves.add(entry.getKey());
                }
            }
            System.out.println(board.halfmoveClock);
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
        System.out.println("Chosen move: " + move[0] + " to " + move[1]);
        return move;
    }
    public double score(ChessPiece[][] board, char team,int moves){
        ChessBoard b=new ChessBoard();
        b.setupBoard(board);
        double score=0;
        int weights[]={0,1,3,3,5,9};
        score+=pieceValues(board,team,weights);//AI adjust
        List<List<Integer>> moveList=b.getAllLegalMoves('w');
        for (List<Integer> move:moveList){
            score+=0.02;//AI adjust
            int fromX=move.get(0);
            int fromY=move.get(1);
            int toX=move.get(2);
            int toY=move.get(3);
            ChessPiece myPiece=board[fromX][fromY];
            ChessPiece theirPiece=board[toX][toY];
            if (theirPiece!=null&&myPiece!=null){
                int myValue=0;
                int theirValue=0;
                switch (myPiece.toString()){
                    case "P": myValue=weights[1]; break;
                    case "N": myValue=weights[2]; break;
                    case "B": myValue=weights[3]; break;
                    case "R": myValue=weights[4]; break;
                    case "Q": myValue=weights[5]; break;
                }
                switch (theirPiece.toString()){
                    case "P": theirValue=weights[1]; break;
                    case "N": theirValue=weights[2]; break;
                    case "B": theirValue=weights[3]; break;
                    case "R": theirValue=weights[4]; break;
                    case "Q": theirValue=weights[5]; break;
                }
                if(theirPiece.getColor()!='w')
                score+=(theirValue-myValue/5/*AI adjust */)*0.5;//AI adjust
                else
                score+=(theirValue-myValue/2/*AI adjust */)*0.9;//AI adjust
            }
        }
        List<List<Integer>> theirMoveList=b.getAllLegalMoves('b');
        for (List<Integer> move:theirMoveList){
            score-=0.02;//AI adjust
            int fromX=move.get(0);
            int fromY=move.get(1);
            int toX=move.get(2);
            int toY=move.get(3);
            ChessPiece theirPiece=board[fromX][fromY];
            ChessPiece myPiece=board[toX][toY];
            if (theirPiece!=null&&myPiece!=null){
                int myValue=0;
                int theirValue=0;
                switch (myPiece.toString()){
                    case "P": myValue=weights[1]; break;
                    case "N": myValue=weights[2]; break;
                    case "B": myValue=weights[3]; break;
                    case "R": myValue=weights[4]; break;
                    case "Q": myValue=weights[5]; break;
                }
                switch (theirPiece.toString()){
                    case "P": theirValue=weights[1]; break;
                    case "N": theirValue=weights[2]; break;
                    case "B": theirValue=weights[3]; break;
                    case "R": theirValue=weights[4]; break;
                    case "Q": theirValue=weights[5]; break;
                }
                if(theirPiece.getColor()!='b')
                score-=(theirValue-myValue/5/*AI adjust */)*0.5;//AI adjust
                else
                score-=(theirValue-myValue/2/*AI adjust */)*0.9;//AI adjust
            }
        }
        score+=1*kingSafety(board,'w');//AI adjust
        score+=-1*kingSafety(board,'b');//AI adjust
        score+=1*rookFiles(board,'w');//AI adjust
        score+=-1*rookFiles(board,'b');//AI adjust
        score+=1*pawnProgress(board,'w');//AI adjust
        score+=-1*pawnProgress(board,'b');//AI adjust
        if (b.isInCheck('w')){
            score-=1;//AI adjust
        }else if(b.isInCheck('b')){
            score+=1;//AI adjust
        }
        if (b.isInStalemate('w')){
            score=0;
            System.out.println("Stalemate detected in scoring");
        }
        if(b.isInStalemate('b')){
            score=0;
            System.out.println("Stalemate detected in scoring");
        }
        if (b.isInsufficientMaterial()){
            score=0;
        }
        if(moves>=98){
            score=0;
        }
        if (b.isInCheckmate('w')){
            score-=10000;
        }else if(b.isInCheckmate('b')){
            b.printBoard();
            System.out.println("Checkmate detected in scoring");
            System.exit(0);
            score+=10000;
        }

        return Math.round(score*1000.0)/1000.0;
    }
    public int pieceValues(ChessPiece[][] board, char team, int weights[]){
        int score=0;
        int pieceValues[]={0,1,3,3,5,9};
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
                    }
                    if (board[r][c].getColor()=='w'){
                        score+=val;
                    }else{
                        score-=val;
                    }
                }
            }
        }        
        return score;
    }
    public static double kingSafety(ChessPiece[][] board, char team){
        ChessBoard b=new ChessBoard();
        b.setupBoard(board);
        double safety=0;
        int kingRow=-1;
        int kingCol=-1;
        for (int r=0;r<8;r++){
            for (int c=0;c<8;c++){
                if (board[r][c]!=null&&board[r][c].toString().equals("K")&&board[r][c].getColor()==team){
                    kingRow=r;
                    kingCol=c;
                }
            }
        }
        if (kingRow!=-1&&kingCol!=-1){
            int[][] directions={{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
            for (int[] dir:directions){
                int newRow=kingRow+dir[0];
                int newCol=kingCol+dir[1];
                if (newRow>=0&&newRow<8&&newCol>=0&&newCol<8){
                    if (board[newRow][newCol]==null||board[newRow][newCol].getColor()==team){
                        safety+=0.5;//AI adjust
                    }else if (board[newRow][newCol].getColor()!=team){
                        safety-=1;//AI adjust
                    }else if(b.isSquareAttacked(newRow,newCol,team=='w'?'b':'w')){
                        safety-=1;//AI adjust
                    }
                }else{
                    safety+=0.6;//AI adjust
                }
            }
        }
        return safety;
    }
    public static double rookFiles(ChessPiece[][] board, char team){
        double openFiles=0;
        for (int c=0;c<8;c++){
            boolean hasRook=false;
            boolean isOpen=true;
            for (int r=0;r<8;r++){
                if (board[r][c]!=null){
                    if (board[r][c].toString().equals("R")&&board[r][c].getColor()==team){
                        hasRook=true;
                    }else if (board[r][c].toString().equals("P")&&board[r][c].getColor()==team){
                        isOpen=false;
                    }
                }
            }
            if (hasRook&&isOpen){
                openFiles+=0.3;//AI adjust
            }
        }
        return openFiles;
    }
    public static double pawnProgress(ChessPiece[][] board, char team){
        double progress=0;
        for (int c=0;c<8;c++){
            for (int r=0;r<8;r++){
                if (board[r][c]!=null&&board[r][c].toString().equals("P")&&board[r][c].getColor()==team){
                    if (team=='w'){
                        progress+=r/10.0;//AI adjust
                    }else{
                        progress+=(7.0-r)/10.0;//AI adjust
                    }
                    if(r==7)
                        progress+=2;//AI adjust
                }
            }
        }
        return progress*1.0;//AI adjust
    }
}
