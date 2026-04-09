import java.util.*;

public class pro {
    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> winsCase = new ArrayList<ArrayList<Character>>();
        ArrayList<ArrayList<Integer>> winsCase2 = new ArrayList<ArrayList<Integer>>();
//        InintilizeCases(winsCase, winsCase2);
        ArrayList<Integer> playerMoves = new ArrayList<>();
//
        Scanner scanner = new Scanner(System.in);

        char[][] matrix = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };


        InintilizeCases(winsCase);
        boardinitialize(matrix);
        int xWins = 0;
        int oWins = 0;
        boolean winner=false;
        System.out.println("Do you wnat to play three round or one ? [1/3]");
        int rounds = scanner.nextInt();
        System.out.println("What role you want to play \'X\' or \'O\'?");
        char playerMark =Character.toUpperCase(scanner.next().charAt(0));
        int roundCount = 1;
        while (true) {
            System.out.println("---------------Round " + roundCount + " Start---------------");
            System.out.println();
            printBoard(matrix);
            InintilizeCases(winsCase);
            while (true) {

                winner=false;
                String Playertype = "human";
                System.out.println(playerMark+" Where you want to play");
                int position = scanner.nextInt();
                //for making move
                int pos = playedMove(matrix, position, playerMark, scanner, Playertype, playerMoves);

                System.out.println("Player "+playerMark+" played in position :" + pos);
                System.out.println();
                if (winnerCheck(matrix, playerMark)) {
                    winner=true;
                    roundCount++;
                    if(playerMark=='X')
                        xWins++;
                    else
                        oWins++;
                    printBoard(matrix);
                    System.out.println();
                    System.out.println("-----"+playerMark+" IS THE WINNER"+"-----");
                    System.out.println();
                    break;
                }
                printBoard(matrix);
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
//                position = random.nextInt(1, 10);
                position = scanner.nextInt();

                Playertype = "Computer";
                pos = playedMove(matrix, position, playerMark, scanner, Playertype, playerMoves);
                System.out.println("Player "+playerMark+" played in position :" + pos);
                System.out.println();
                if (winnerCheck(matrix, playerMark)) {
                    winner=true;
                    roundCount++;
                    if(playerMark=='X')
                        xWins++;
                    else
                        oWins++;
                    printBoard(matrix);
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

//                System.out.println(winsCase);
                printBoard(matrix);
            }
            if (rounds == 1)
                break;
            if (xWins >= 2 || (xWins>oWins && roundCount > 3)) {
                System.out.println();
                System.out.println("----- O WON "+ xWins+" ROUND OUT OF 3 -----");
                System.out.println("GAME OVER");
                System.out.println();
                break;
            }
            if (oWins >= 2 || (oWins>xWins && roundCount > 3)) {
                System.out.println();
                System.out.println("----- O WON "+ oWins+" ROUND OUT OF 3 -----");
                System.out.println("GAME OVER");
                System.out.println();
                break;
            }
            if(roundCount > 3 && xWins==oWins){
                System.out.println();
                System.out.println("DRAW ");
                System.out.println("X WON " + xWins + " TIME");
                System.out.println("O WON " + oWins + " TIME");
                System.out.println();
                break;
            }
            if (roundCount > 3) {
                break;
            }
            startAgain(matrix, winsCase, playerMoves);
        }
        System.out.println("THE LAST BOARD");
        printBoard(matrix);

    }

    private static void boardinitialize(char[][] matrix) {
        int counter = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char ch = (char) (counter + '0');
                counter++;
                matrix[i][j] = ch;
            }
        }
    }

    private static void startAgain(char[][] board, ArrayList<ArrayList<Character>> winsCase, ArrayList<Integer> playerMoves) {
        boardinitialize(board);
        InintilizeCases(winsCase);
        playerMoves.clear();
//        System.out.println(winsCase);
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

    private static int playedMove(char[][] matrix, int position,char player,Scanner scanner,String Playertype,
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

        for (char[] num : matrix) {
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
    private static void InintilizeCases(ArrayList<ArrayList<Character>> winsCase) {
        winsCase.clear();
        ArrayList<Character> cases1=new ArrayList<>();
        cases1.add('1');
        cases1.add('2');
        cases1.add('3');
        ArrayList<Character> cases2=new ArrayList<>();
        cases2.add('4');
        cases2.add('5');
        cases2.add('6');
        ArrayList<Character> cases3=new ArrayList<>();
        cases3.add('7');
        cases3.add('8');
        cases3.add('9');
        ArrayList<Character> cases4=new ArrayList<>();
        cases4.add('1');
        cases4.add('4');
        cases4.add('7');
        ArrayList<Character> cases5=new ArrayList<>();
        cases5.add('2');
        cases5.add('5');
        cases5.add('8');
        ArrayList<Character> cases6=new ArrayList<>();
        cases6.add('3');
        cases6.add('6');
        cases6.add('9');
        ArrayList<Character> cases7=new ArrayList<>();
        cases7.add('1');
        cases7.add('5');
        cases7.add('9');
        ArrayList<Character> cases8=new ArrayList<>();
        cases8.add('3');
        cases8.add('5');
        cases8.add('7');
        winsCase.add(cases1);
        winsCase.add(cases2);
        winsCase.add(cases3);
        winsCase.add(cases4);
        winsCase.add(cases5);
        winsCase.add(cases6);
        winsCase.add(cases7);
        winsCase.add(cases8);

    }
    private static void printBoard(char[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < matrix.length ; j++) {

                if(j== matrix.length-1){
                    System.out.print(matrix[i][j]);
                    System.out.println();
                }else
                    System.out.print(matrix[i][j]+ " | ");


            }
            if(i!=2)
            System.out.println("----------");
        }
    }
}
