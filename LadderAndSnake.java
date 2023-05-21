/*
 * This Java code defines a LadderAndSnake class which implements a simple game of Snakes and Ladders. 
 * The game has a board with 100 cells, arranged in a 10x10 grid, where each cell represents a specific number. 
 * The game supports up to 2 players and takes turns rolling a dice. The dice value determines the number of 
 * steps each player moves on the board. The goal of the game is to reach cell 100, which is the last cell on 
 * the board. If a player lands on a cell with a ladder, they will climb up the ladder to a higher cell, and 
 * if a player lands on a cell with a snake, they will slide down the snake to a lower cell. The code uses 
 * the Scanner and Random classes to handle input and dice rolls, and the Thread class to pause the game execution.
 */

/**
@author Eric Chen
@version 1
*/

import java.util.Scanner;
import java.lang.Thread;
import java.util.Random;

/**
Class LadderAndSnake represents the game of Ladder and Snake.
It contains methods to initialize and play the game.
*/
public class LadderAndSnake {

    // Instance variables
    private Object[][] arr = new Object[10][10];
    private int numPlayers;    
    
    /** 
     * @return int
     * Flip the dice and return a number between 1 and 6 (inclusive)
     */
    public static int flipDice() {
        int n = 0;
        Random dice = new Random();
        n = dice.nextInt(6) + 1;
        return n;
    }

    /**
    Verifies that the number of players is 2.
    If less than 2, the game will exit.
    If more than 2, it will forcefully set the game to 2 players and disregard the number inputted by the user.
    */
    public LadderAndSnake(int numPlayers) throws InterruptedException{

        if (numPlayers < 2) 
        {
            Thread.sleep(700);
            System.out.println("|=========================================================================================|");
            System.out.println("|           Error: Cannot execute the game with less than 2 players! Will exit.           |");
            System.out.println("|=========================================================================================|");
            System.out.println();
            System.exit(0);
        }
        else if (numPlayers > 2) {
            Thread.sleep(700);
            System.out.println("|=========================================================================================|");
            System.out.println("|       Initialization was attempted for " + numPlayers +" players; however, this is only expected        |\n"
                              +"|                for an extended version the game. Value will be set to 2.                |");   
            System.out.println("|=========================================================================================|");
            System.out.println("\n");
            this.numPlayers = 2;
        }
        else {
            this.numPlayers = numPlayers;
        }
    }

    /**
    Fills up a 2D array with the numbers 1 to 100 in the form of a snake.
    Left to right, then right to left.
    */
    public LadderAndSnake() {
        
        
        int count = 100;

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 10; j++) {
                    arr[i][j] = count;
                    count--;
                }
            } else {
                for (int j = 9; j >= 0; j--) {
                    arr[i][j] = count;
                    count--;
                }
            }
        }
        
    }


    /**
    Prints the game board in the terminal.
    Depending on the number it will print the spacing differently.
    In addition, the general display of this board is made to be aesthetically pleasing with symbols.
    */
    public void printBoard() {
        
        System.out.println("|=========================================================================================|");
        System.out.println("|                                  Snake and Ladder Board                                 |");
        System.out.println("|=========================================================================================|");
        for (int i = 0; i < 10; i++) { // rows
            for (int j = 0; j < 10; j++) { // columns

                Object value = arr[i][j];

                if (value instanceof Integer) {

                    if ((int) value == 100) {
                        System.out.print("|   " + arr[i][j] + "  |   ");
                    } else if ((int) value < 10) {
                        System.out.print(arr[i][j] + "    |   ");
                    } else if ((int) value != 1) {
                        System.out.print(arr[i][j] + "   |   ");
                    } else if ((int) value == 1) {
                        System.out.print("|   " + arr[i][j] + "    |   ");
                    }

                }
                

            } // end of columns

            if (i<9)
            System.out.print("\n|-----------------------------------------------------------------------------------------|");
            
            if (i==9)
            System.out.print("\n|=========================================================================================|");

            if (i + 1 < 10) {
                System.out.print("\n|   ");
            }
        } // end of rows
        
    }
    
    
    /** 
     * @throws InterruptedException
     * The whole game will execute with the following method
     */
    public static void play() throws InterruptedException{

        // Instance variables
        String str;
        int diceValue1;
        int diceValue2;
        int attempts = 1;

        Scanner keyIn = new Scanner(System.in);

        // Short delay to allow user to view message
        Thread.sleep(700);

        // Prompt user with instructions to assign names to the players
        // The names will be stored being inputted
        System.out.print("|=========================================================================================|");
        System.out.print("\n                            Time to assign the player numbers!");
        System.out.print("\n                        Player 1, please write your name: ");
        String name1 = keyIn.next();
        Player player1 = new Player(name1);

        System.out.print("                        Player 2, please write your name: ");
        String name2 = keyIn.next();

        Player player2 = new Player(name2);

        // Prompt the user with further instructions about how the game will work
        // The user who rolls the highest number shall start first
        System.out.print("|=========================================================================================|");
        System.out.println("\n              Before the game begins, let's see which player begins first!");
        System.out.println("            The player who rolls the highest number shall decide the order.");
        System.out.println("    When your player number and name appears, please roll the dice by pressing any key." + "\n");
        
        // Demands player 1 to roll the dice
        // Stores the number they rolled
        System.out.print("P1 ("+ player1.getName() + "): ");
        str = keyIn.next();
        diceValue1 = LadderAndSnake.flipDice();
        System.out.print(" --------   You rolled [" + diceValue1 + "]" + "\n");

        // Demands player 2 to roll the dice
        // Stores the number they rolled
        System.out.print("P2 ("+ player2.getName() + "): ");
        str = keyIn.next();
        diceValue2 = LadderAndSnake.flipDice();
        System.out.print(" --------   You rolled [" + diceValue2 + "]" + "\n");

        // If both players roll the same number, they will be asked to roll again until someone has a higher number
        while (diceValue1 == diceValue2) {
            System.out.println("A tie was achieved between P1 ("+ player1.getName() + "): and P2 (" + player2.getName() + "). Please roll again!");

            System.out.print("P1 ("+ player1.getName() + "): ");
            str = keyIn.next();
            diceValue1 = LadderAndSnake.flipDice();
            System.out.print(" --------   You rolled [" + diceValue1 + "]" + "\n");

            System.out.print("P2 ("+ player2.getName() + "): ");
            str = keyIn.next();
            diceValue2 = LadderAndSnake.flipDice();
            System.out.print(" --------   You rolled [" + diceValue2 + "]" + "\n");

            // Will calculate the amount of attempts it took to reach a consensus
            attempts++;
        }

        // If player 1 rolled a higher number, they will start first
        if (diceValue1 > diceValue2) {
            System.out.println();
            System.out.println("P1 (" + player1.getName() + ") rolled [" + diceValue1 + "] which is bigger than P2's (" + player2.getName() + ") [" + diceValue2 + "]. They will start first!");
            if (attempts > 1) {
            System.out.println("It took " + attempts + " attempts before a decision could be made.");
            }
        }

        // If player 2 rolled a higher number, they will start first
        else if (diceValue1 < diceValue2) {
            System.out.println();
            System.out.println("P2 (" + player2.getName() + ") rolled [" + diceValue2 + "] which is bigger than P1's (" + player1.getName() + ") [" + diceValue1 + "]. They will start first!");
            if (attempts > 1) {
            System.out.println("It took " + attempts + " attempts before a decision could be made.");
            }
        }
        
        // Displays a message that the game board will be generated
        // Makes the gameplay more immersive 
        System.out.println();
        System.out.println("             -----------------------------------------------------------------");
        System.out.println("                  <<< Generating Snake and Ladder Board, please wait >>>\n");

            
            // Creates the board and prints it after a delay
            LadderAndSnake game = new LadderAndSnake();
            Thread.sleep(2000);
            game.printBoard();
            
            // Players will roll the dice and repeat this process until a player wins
            // The loop will not end until a player reaches square 100
            do {
                
                // Initializing the dice values for the game
                int gdiceValue1 = 0;
                int gdiceValue2 = 0;

                System.out.println();

                // If player 1 rolled a higher number previously, they will start first and this order will repeat throughout the game
                if (diceValue1 > diceValue2) {
                    System.out.print("P1 (" + player1.getName() + ") turn, roll the dice: ");
                    str = keyIn.next();
                    gdiceValue1 = LadderAndSnake.flipDice();

                    // Sets end position to start position + dice roll
                    player1.setEndPos(gdiceValue1+player1.getStartPos()); 
                    // Displays the result and the new position of the player
                    System.out.print("You rolled [" + gdiceValue1 + "]. You have now moved up to square [" + player1.getEndPos() + "]\n");
                    // Sets the start position of the player to their previous end position for the next round
                    player1.setStartPos(player1.getEndPos());
                    // Checks if the player reached a number over 100
                    player1.exceedHundred();
                    // Checks if the player encountered a ladder
                    player1.checkLadders();
                    // Checks if the player encountered a snake
                    player1.checkSnakes();
                    // Checks if the player encountered another player
                    // If they do, they will kick out the other player of that position and reset their position to 0
                    player1.checkPlayerCollision(player2);
                    // If a player reaches 100, they will immediately be declared the winner
                    // This will also result in the end of the game
                    if (player1.getStartPos() == 100) { 
                        System.out.println("===========================================================================================");
                        System.out.println("                        Congrats Player 1 " + player1.getName() + "! You won the game.");
                        System.out.println("===========================================================================================");
                        break;
                    }

                    // ===================================================================================================================

                    System.out.print("P2 (" + player2.getName() + ") turn, roll the dice: ");
                    str = keyIn.next();
                    gdiceValue2 = LadderAndSnake.flipDice();

                    player2.setEndPos(gdiceValue2+player2.getStartPos());
                    System.out.print("You rolled [" + gdiceValue2 + "]. You have now moved up to square [" + player2.getEndPos() + "]\n");
                    player2.setStartPos(player2.getEndPos());
                    player2.exceedHundred();
                    player2.checkLadders();
                    player2.checkSnakes();
                    player2.checkPlayerCollision(player1);
                    if (player2.getStartPos() == 100) {
                        System.out.println("===========================================================================================");
                        System.out.println("                        Congrats Player 2 " + player2.getName() + "! You won the game.");
                        System.out.println("===========================================================================================");
                        break;
                    }
                }

                // If player 2 rolled a higher number previously, they will start first and this order will repeat throughout the game
                else if (diceValue1 < diceValue2) {
                    System.out.print("P2 (" + player2.getName() + ") turn, roll the dice: ");
                    str = keyIn.next();
                    gdiceValue2 = LadderAndSnake.flipDice();

                    player2.setEndPos(gdiceValue2+player2.getStartPos());
                    System.out.print("You rolled [" + gdiceValue2 + "]. You have now moved up to square [" + player2.getEndPos() + "]\n");
                    player2.setStartPos(player2.getEndPos());
                    player2.exceedHundred();
                    player2.checkLadders();
                    player2.checkSnakes();
                    player2.checkPlayerCollision(player1);
                    if (player2.getStartPos() == 100) {
                        System.out.println("===========================================================================================");
                        System.out.println("                        Congrats Player 2 " + player2.getName() + "! You won the game.");
                        System.out.println("===========================================================================================");
                        break;
                    }

                    // ===================================================================================================================

                    System.out.print("P1 (" + player1.getName() + ") turn, roll the dice: ");
                    str = keyIn.next();
                    gdiceValue1 = LadderAndSnake.flipDice();

                    player1.setEndPos(gdiceValue1+player1.getStartPos());
                    System.out.print("You rolled [" + gdiceValue1 + "]. You have now moved up to square [" + player1.getEndPos() + "]\n");
                    player1.setStartPos(player1.getEndPos());
                    player1.exceedHundred();
                    player1.checkLadders();
                    player1.checkSnakes();
                    player1.checkPlayerCollision(player2);
                    if (player1.getStartPos() == 100) { 
                        System.out.println("===========================================================================================");
                        System.out.println("                        Congrats Player 1 " + player1.getName() + "! You won the game.");
                        System.out.println("===========================================================================================");
                        break;
                    }
                }
            
            
                
            } while (true);
    }
}
