import java.io.*;
import java.util.Scanner;

public class Menu extends TicTacToe{

    Scanner menuInput = new Scanner(System.in);
    public boolean exit = false;


    public void runMenu() {
        while (!exit) {
            menuHeader();
            menuScoreboard();
            mainMenu();
            int choice = -1;
            choice = getInput();
            menuAction(choice);
        }
    }

    private void menuHeader() {
        System.out.println("====================");
        System.out.println("Welcome to TicTacToe");
        System.out.println("====================\n");
    }

    private void menuScoreboard() {
        System.out.println("Name      Games Won");
        System.out.println("-------------------");
        System.out.println("Player        " + playerWins);
        System.out.println("Bot           " + botWins);
    }

    private void mainMenu() {
        System.out.println("\nPlease choose an option below: ");
        System.out.println("\n(1) Play Game");
        System.out.println("(0) Exit Game");

    }

    private int getInput() {

        int choice = -1;
        while (choice < 0 || choice > 2) {
            try {
                System.out.println("\nEnter your option: ");
                choice = Integer.parseInt(menuInput.next());
                if (choice > 2) {
                    System.out.println("\nYou have entered an invalid option. Please try again.");
                } else if (choice < 0) {
                    System.out.println("\nYou have entered an invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nYou have not entered an integer value. Please try again.");
            }
        }
        return choice;
    }

    private void menuAction(int choice) {
        switch (choice) {
            case 0:
                exit = true;
                System.out.println("\nThank you for using this application. Goodbye.");
                writeScoreboardData();
                break;
            case 1:
                startGame();
                break;
            default:
                break;
        }
    }

    public void getScoreboardData(){
        try {
            File file = new File("C:/Users/ashnr/Desktop/TicTacToe/src/scoreboard.txt");
            Scanner scan = new Scanner(file);

            playerWins = scan.nextInt();
            botWins = scan.nextInt();
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeScoreboardData() {
        try {
            PrintStream fileStream = new PrintStream(new File("C:/Users/ashnr/Desktop/TicTacToe/src/scoreboard.txt"));
            fileStream.println(playerWins);
            fileStream.println(botWins);
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
