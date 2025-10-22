package Fun.Chess;

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
            System.out.println("Current board score: " + score(board.copyBoard(), team, board.halfmoveClock, new double[]{0.0,1.0,3.0,3.0,5.0,9.0,0.02}));
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
                double[] weights={0.0,1.0,3.0,3.0,5.0,9.0,0.04};//AI adjust
                System.out.println("Testing move: " + (char)('a' + startcol) + (8 - startrow) + " to " + (char)('a' + endcol) + (8 - endrow));
                scores.put(m,score(testboard,team,moves,weights));
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
                //System.out.println(minimaxscore + " " + entry.getValue() + " " + fromNotation + " " + toNotation);
                if (team=='w'?entry.getValue()>minimaxscore:entry.getValue()<minimaxscore){
                    minimaxscore=entry.getValue();
                    bestMoves.clear();
                    bestMoves.add(entry.getKey());
                }else if(entry.getValue()==minimaxscore){
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
        System.out.println("Chosen move: " + move[0] + " to " + move[1]);
        return move;
    }
    public double score(ChessPiece[][] board, char team,int moves,double[] weights){
        ChessBoard b=new ChessBoard();
        b.setupBoard(board);
        double score=0;
        score+=pieceValues(board,team,weights)*2;//AI adjust
        List<List<Integer>> moveList=b.getAllLegalMoves('w');
        for (List<Integer> move:moveList){
            score+=weights[6];
            int fromX=move.get(0);
            int fromY=move.get(1);
            int toX=move.get(2);
            int toY=move.get(3);
            if(toX>=3&&toX<=4&&toY>=3&&toY<=4){
                score+=weights[6];
            }
            ChessPiece myPiece=board[fromX][fromY];
            ChessPiece theirPiece=board[toX][toY];
            if (theirPiece!=null&&myPiece!=null){
                double myValue=0;
                double theirValue=0;
                switch (myPiece.toString()){
                    case "K": myValue=0; break;
                    case "P": myValue=weights[1]; break;
                    case "N": myValue=weights[2]; break;
                    case "B": myValue=weights[3]; break;
                    case "R": myValue=weights[4]; break;
                    case "Q": myValue=weights[5]; break;
                }
                switch (theirPiece.toString()){
                    case "k": theirValue=0; break;
                    case "p": theirValue=weights[1]; break;
                    case "n": theirValue=weights[2]; break;
                    case "b": theirValue=weights[3]; break;
                    case "r": theirValue=weights[4]; break;
                    case "q": theirValue=weights[5]; break;
                }
                if(theirPiece.getColor()!='w')
                score+=(theirValue/*AI adjust */)*0.9;//AI adjust
                else
                score+=(theirValue/*AI adjust */)*0.5;//AI adjust
                score+=myValue-myValue;
            }
        }
        moveList=b.getAllLegalMoves('b');
        for (List<Integer> move:moveList){
            score-=weights[6];
            int fromX=move.get(0);
            int fromY=move.get(1);
            int toX=move.get(2);
            int toY=move.get(3);
            if(toX>=3&&toX<=4&&toY>=3&&toY<=4){
                score-=weights[6];
            }
            ChessPiece theirPiece=board[fromX][fromY];
            ChessPiece myPiece=board[toX][toY];
            if (theirPiece!=null&&myPiece!=null){
                double myValue=0;
                double theirValue=0;
                switch (myPiece.toString()){
                    case "k": myValue=0; break;
                    case "p": myValue=weights[1]; break;
                    case "n": myValue=weights[2]; break;
                    case "b": myValue=weights[3]; break;
                    case "r": myValue=weights[4]; break;
                    case "q": myValue=weights[5]; break;
                }
                switch (theirPiece.toString()){
                    case "K": theirValue=0; break;
                    case "P": theirValue=weights[1]; break;
                    case "N": theirValue=weights[2]; break;
                    case "B": theirValue=weights[3]; break;
                    case "R": theirValue=weights[4]; break;
                    case "Q": theirValue=weights[5]; break;
                }
                if(theirPiece.getColor()!='b')
                score-=(theirValue/*AI adjust */)*0.9;//AI adjust
                else
                score-=(theirValue/*AI adjust */)*0.5;//AI adjust
                score+=myValue-myValue;
            }
        }
        score+=0.3*kingSafety(board,'w');//AI adjust
        score-=0.3*kingSafety(board,'b');//AI adjust
        score+=0.5*rookFiles(board,'w');//AI adjust
        score-=0.5*rookFiles(board,'b');//AI adjust
        score+=0.9*pawnProgress(board,'w');//AI adjust
        score-=0.9*pawnProgress(board,'b');//AI adjust
        if (b.isInCheck('w')){
            score-=0.3;//AI adjust
        }else if(b.isInCheck('b')){
            score+=0.3;//AI adjust
        }
        if (b.isInStalemate('w')){
            score=0;
        }
        if(b.isInStalemate('b')){
            score=0;
        }
        if (b.isInsufficientMaterial()){
            score=0;
        }
        if(moves>=98){
            score=0;
        }
        if (b.isInCheckmate('w')){
            score-=100000;
        }else if(b.isInCheckmate('b')){
            score+=100000;
        }
        if(b.onlyQueen('w')){
            board=b.getBoard();
            score+=100*queenSolve(board,'w');
            if(b.isInStalemate('b')){
                score-=500;
            }
        }else if(b.onlyQueen('b')){
            board=b.getBoard();
            score-=100*queenSolve(board,'b');
            if(b.isInStalemate('w')){
                score+=500;
            }
        }
        if(b.onlyPawn('w')){
            board=b.getBoard();
            score+=100*pawnSolve(board,'w');
        }
        if(b.onlyPawn('b')){
            board=b.getBoard();
            score-=100*pawnSolve(board,'b');
        }
        return Math.round(score*1000.0)/1000.0;
    }
    public int queenSolve(ChessPiece[][] board, char team){
        
        int qX=-1;
        int qY=-1;
        int kX=-1;
        int kY=-1;
        int oX=-1;
        int oY=-1;
        int score=0;
        for (int r=0;r<8;r++){
            for (int c=0;c<8;c++){
                if (board[r][c]!=null){
                    if ((board[r][c].toString().equals("Q")||board[r][c].toString().equals("q"))&&board[r][c].getColor()==team){
                        qY=r;
                        qX=c;
                    }
                    if ((board[r][c].toString().equals("K")||board[r][c].toString().equals("k"))&&board[r][c].getColor()==team){
                        kY=r;
                        kX=c;
                    }
                    if ((board[r][c].toString().equals("K")||board[r][c].toString().equals("k"))&&board[r][c].getColor()!=team){
                        oY=r;
                        oX=c;
                    }
                }
            }
        }
        int distY=qY-oY;
        int distX=oX-qX;
        if(distY==1&&distX==2){
            score+=5;
        }
        if(qY==1&&qX==4&&oY==0&&oX>6){
            score+=10;
            score-=Math.abs(2-kY);
            score-=Math.abs(6-kX);
        }
        score-=Math.abs(oY-qY);
        score-=Math.abs(oX-qX);
        if (Math.abs(oY-qY)<=1&&Math.abs(oX-qX)<=1){
            score-=13;
        }
        return score;
    }
    public double pawnSolve(ChessPiece[][] board, char team){
        int pX=-1;
        int pY=-1;
        int kX=-1;
        int kY=-1;
        double score=0;
        for (int r=0;r<8;r++){
            for (int c=0;c<8;c++){
                if (board[r][c]!=null){
                    if ((board[r][c].toString().equals("P")||board[r][c].toString().equals("p"))&&board[r][c].getColor()==team){
                        pY=r;
                        pX=c;
                    }
                    if ((board[r][c].toString().equals("K")||board[r][c].toString().equals("k"))&&board[r][c].getColor()==team){
                        kY=r;
                        kX=c;
                    }
                }
            }
        }
        if (team=='b'){
            score+=pY*2;
            score-=Math.abs(kX - pX)*2.5;
            score-=Math.abs(kY - pY)*2.5;
            if (pY==7){
                score+=20;
            }
        }else{
            score+=(7 - pY)*2;
            score-=Math.abs(kX - pX)*2.5;
            score-=Math.abs(kY - pY)*2.5;
            if (pY==0){
                score+=20;
            }
        }
        return score;
    }
    public double pieceValues(ChessPiece[][] board, char team, double weights[]){
        double score=0;
        for (int r=0;r<8;r++){
            for (int c=0;c<8;c++){
                if (board[r][c]!=null){
                    double val=0;
                    switch (board[r][c].toString()){
                        case "P": val=weights[1]; break;
                        case "p": val=weights[1]; break;
                        case "N": val=weights[2]; break;
                        case "n": val=weights[2]; break;
                        case "B": val=weights[3]; break;
                        case "b": val=weights[3]; break;
                        case "R": val=weights[4]; break;
                        case "r": val=weights[4]; break;
                        case "Q": val=weights[5]; break;
                        case "q": val=weights[5]; break;
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
                if (board[r][c]!=null&&(board[r][c].toString().equals("K")||board[r][c].toString().equals("k"))&&board[r][c].getColor()==team){
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
                    safety+=0.5;//AI adjust
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
                if (board[r][c]!=null&&(board[r][c].toString().equals("P")||board[r][c].toString().equals("p"))&&board[r][c].getColor()==team){
                    if (team=='b'){
                        progress+=r/10.0;//AI adjust
                    }else{
                        progress+=(7.0-r)/10.0;//AI adjust
                    }
                    if(r==7||r==0){
                        progress+=2;//AI adjust
                    }
                }
            }
        }
        return progress*1.5;//AI adjust
    }
}
