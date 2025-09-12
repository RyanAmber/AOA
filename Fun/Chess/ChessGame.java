package Fun.Chess;
import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scanner = new Scanner(System.in);
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
            System.out.print("Enter move (e.g., e2 e4): ");
            String from = scanner.next();
            String to = scanner.next();

            boolean moveResult = board.movePiece(from, to, currentPlayer, scanner);
            if (moveResult) {
                currentPlayer = (currentPlayer == 'w') ? 'b' : 'w';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
