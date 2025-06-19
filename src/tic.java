import java.util.Scanner;

public class tic {
    public static void main(String[] args) {
        char [] [] board = new char [][] {
                {' ',' ',' '},{' ',' ',' '},{' ',' ',' '}
        };
        Scanner sc = new Scanner(System.in);

        char Player = 'X';
        boolean won = false;
        while (!won){
            showBoard(board);
            boolean valid = true;
            System.out.println("Enter the Row and Col for Player "+ Player);
            int r = sc.nextInt();
            int c = sc.nextInt();

            if (board[r][c] == ' '){
                board[r][c] = Player;
                won = wonTheGame(board,Player);
            }
            else {
                System.out.println("Wrong move !!");
                valid = false;
            }
            if (won){
                System.out.println("Player "+Player+" won!");
            }
            if (valid){
                if (Player == 'X'){
                    Player = 'O';

                }
                else {
                    Player = 'X';
                }
            }

        }
        showBoard(board);
    }

        private static boolean wonTheGame(char[][] board, char player) {

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == player&& board[i][1] == player && board[i][2] == player){
                return true;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == player&& board[1][i] == player && board[2][i] == player){
                return true;
            }
        }
        if (board[0][0] == player&& board[1][1] == player && board[2][2] == player){
            return true;
        }
        if (board[0][2] == player&& board[1][1] == player && board[2][0] == player){
            return true;
        }

        return false;
    }
    static void showBoard(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();

        }
    }

}
