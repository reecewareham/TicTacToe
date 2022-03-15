import java.util.*;

public class TicTacToe {

    public static char [] [] cleanGameBoard = {{' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

    public static char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> botPositions = new ArrayList<Integer>();

    public static int playerWins = 0;
    public static int botWins = 0;

    public static void printBoard(char [] [] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placement(char[][] gameBoard, int position, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("bot")) {
            symbol = 'O';
            botPositions.add(position);
        }

        switch(position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        List firstRow = Arrays.asList(1,2,3);
        List secondRow = Arrays.asList(4,5,6);
        List thirdRow = Arrays.asList(7,8,9);
        List firstColumn = Arrays.asList(1,4,7);
        List secondColumn = Arrays.asList(2,5,8);
        List thirdColumn = Arrays.asList(3,6,9);
        List firstDiagonal = Arrays.asList(1,5,9);
        List secondDiagonal = Arrays.asList(7,5,3);

        List<List> winningPositions = new ArrayList<List>();
        winningPositions.add(firstRow);
        winningPositions.add(secondRow);
        winningPositions.add(thirdRow);
        winningPositions.add(firstColumn);
        winningPositions.add(secondColumn);
        winningPositions.add(thirdColumn);
        winningPositions.add(firstDiagonal);
        winningPositions.add(secondDiagonal);

        for(List l : winningPositions) {
            if (playerPositions.containsAll(l)) {
                playerWins++;
                gameBoard = cleanGameBoard;
                return "Player Wins";
            } else if (botPositions.containsAll(l)) {
                botWins++;
                gameBoard = cleanGameBoard;
                return "Bot Wins";
            } else if (playerPositions.size() + botPositions.size() == 9) {
                gameBoard = cleanGameBoard;
                return "Tie Game";
            }
        }

        return "";
    }

    public static void startGame() {

        printBoard(gameBoard);

        while(true) {
            Scanner input = new Scanner(System.in);

            System.out.println("Choose a placement (1-9) : ");
            int playerPosition = input.nextInt();
            while(playerPositions.contains(playerPosition) || botPositions.contains(playerPositions)) {
                System.out.println("Position already taken!");
                playerPosition = input.nextInt();
            }
            placement(gameBoard,playerPosition,"player");

            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int botPosition = rand.nextInt(9) + 1;
            while(playerPositions.contains(botPosition) || botPositions.contains(botPositions)) {
                botPosition = rand.nextInt(9) + 1;
            }
            placement(gameBoard,botPosition,"bot");

            printBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }
    }


}
