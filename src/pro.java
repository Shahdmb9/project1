import java.util.*;

public class pro {
    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> winsCase = new ArrayList<ArrayList<Character>>();

        ArrayList<Integer> playerMoves = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        char[][] board = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };
        int xWins = 0;
        int oWins = 0;
        System.out.println("Do you wnat to play three round or one ? [1/3]");
        int rounds = scanner.nextInt();
        System.out.println("What role you want to play \'X\' or \'O\'?");
        char playerMark =Character.toUpperCase(scanner.next().charAt(0));
        int roundCount = 1;

        while (true) {

            System.out.println("---------------Round " + roundCount + " Start---------------");
            System.out.println();
            printBoard(board);

            while (true) {
                String Playertype = "human";
                System.out.println(playerMark+" Where you want to play");
                int position = scanner.nextInt();
                //for making move
                int pos = playedMove(board, position, playerMark, scanner, Playertype, playerMoves);

                System.out.println("Player "+playerMark+" played in position :" + pos);
                System.out.println();
                if (winnerCheck(board, playerMark)) {
                    roundCount++;
                    if(playerMark=='X')
                        xWins++;
                    else
                        oWins++;
                    printBoard(board);
                    System.out.println();
                    System.out.println("-----"+playerMark+" IS THE WINNER"+"-----");
                    System.out.println();
                    break;
                }
                printBoard(board);
                if (playerMoves.size() == 9) {
                    roundCount++;
                    System.out.println();
                    System.out.println("-----Draw-----");
                    System.out.println();
                    break;
                }

                playerMark=playerMark=='X'?'O':'X';
                System.out.println(playerMark+" Turn");
                Random random = new Random();
                position = random.nextInt(1, 10);
//                position = scanner.nextInt();
                Playertype = "Computer";
                pos = playedMove(board, position, playerMark, scanner, Playertype, playerMoves);
                System.out.println("Player "+playerMark+" played in position :" + pos);
                System.out.println();
                if (winnerCheck(board, playerMark)) {
                    roundCount++;
                    if(playerMark=='X')
                        xWins++;
                    else
                        oWins++;
                    printBoard(board);
                    System.out.println();
                    System.out.println("-----"+playerMark+" IS THE WINNER"+"-----");
                    System.out.println();
                    playerMark=playerMark=='X'?'O':'X';
                    break;
                }
                if (playerMoves.size() == 9) {
                    roundCount++;
                    System.out.println();
                    System.out.println("-----Draw-----");
                    System.out.println();
                    break;
                }
                playerMark=playerMark=='X'?'O':'X';

                printBoard(board);
            }
            if (rounds == 1)
                break;
            if (xWins >= 2 || (xWins>oWins && roundCount > 3)) {
                System.out.println();
                System.out.println("GAME OVER");
                System.out.println("----- X WON "+ xWins+" ROUND OUT OF 3 -----");
                System.out.println("----- O WON "+ oWins+" ROUND OUT OF 3 -----");
                System.out.println();
                break;
            }
            if (oWins >= 2 || (oWins>xWins && roundCount > 3)) {
                System.out.println();
                System.out.println("GAME OVER");
                System.out.println("----- X WON "+ xWins+" ROUND OUT OF 3 -----");
                System.out.println("----- O WON "+ oWins+" ROUND OUT OF 3 -----");
                System.out.println();
                break;
            }
            if(roundCount > 3 && xWins==oWins){
                System.out.println();
                System.out.println("DRAW");
                System.out.println("----- X WON "+ xWins+" ROUND OUT OF 3 -----");
                System.out.println("----- O WON "+ oWins+" ROUND OUT OF 3 -----");
                System.out.println();
                break;
            }
            if (roundCount > 3) {
                break;
            }
            startAgain(board, playerMoves);
        }
        System.out.println("THE LAST BOARD");
        printBoard(board);

    }

    private static void boardinitialize(char[][] board) {
        int counter = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char ch = (char) (counter + '0');
                counter++;
                board[i][j] = ch;
            }
        }
    }

    private static void startAgain(char[][] board, ArrayList<Integer> playerMoves) {
        boardinitialize(board);
        playerMoves.clear();
    }

    private static Boolean winnerCheck(char[][] board, char Mark) {

        for(int i = 0; i< 3;i++) {
        if (board[i][0] == Mark && board[i][1] == Mark && board[i][2] == Mark) {
            return true;
        }
        if (board[0][i] == Mark && board[1][i] == Mark && board[2][i] == Mark) {
            return true;
        }
    }

    if(board[0][0]==Mark &&board[1][1]==Mark &&board[2][2]==Mark){
        return true;
    }
    if(board[0][2]==Mark &&board[1][1]==Mark &&board[2][0]==Mark){
        return true;
    }
    return false;
}

    private static int playedMove(char[][] board, int position,char player,Scanner scanner,String Playertype,
                                  ArrayList<Integer> playerMoves) {
        while(playerMoves.contains(position)){
            if(Playertype.equalsIgnoreCase("human")) {
                System.out.println("Invalid place enter again");
                position = scanner.nextInt();
            }else {
                Random random = new Random();
                position = random.nextInt(1, 10);
                System.out.println(position);
            }
        }

        for (char[] num : board) {
                for (int i = 0; i < num.length; i++) {
                    if (Character.getNumericValue(num[i]) == position) {
                        playerMoves.add(position);
                        num[i] = Character.toUpperCase(player);
                        return position;
                    }
            }
        }
        return position;
    }
    private static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < board.length ; j++) {

                if(j== board.length-1){
                    System.out.print(board[i][j]);
                    System.out.println();
                }else
                    System.out.print(board[i][j]+ " | ");


            }
            if(i!=2)
            System.out.println("----------");
        }
    }
}
