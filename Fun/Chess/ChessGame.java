package Fun.Chess;
import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who is player one? [1] Human, [2] Robot, [3] Random");
        Player p1=new Player(scanner.nextInt());
        System.out.println("Who is player two? [1] Human, [2] Robot, [3] Random");
        Player p2=new Player(scanner.nextInt());
        char currentPlayer = 'w';

        while (true) {
            board.printBoard();
            if (board.isInCheckmate(currentPlayer)) {
                System.out.println((currentPlayer == 'w' ? "White" : "Black") + " is in checkmate. Game over!");
                break;
            }
            if (board.isInStalemate(currentPlayer)) {
                System.out.println("Stalemate! Game over!");
                break;
            }
            if (board.isInCheck(currentPlayer)) {
                System.out.println((currentPlayer == 'w' ? "White" : "Black") + " is in check!");
            }

            System.out.println((currentPlayer == 'w' ? "White" : "Black") + "'s move.");
            String[] move=new String[2];
            if (currentPlayer=='w'){
                move=p1.getMove(board);
            }else{
               move=p2.getMove(board);
            }

            boolean moveResult = board.movePiece(move[0], move[1], currentPlayer, scanner);
            if (moveResult) {
                currentPlayer = (currentPlayer == 'w') ? 'b' : 'w';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
