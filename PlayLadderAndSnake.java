/**
@author Eric Chen
@version 1
*/

import java.util.Scanner;

public class PlayLadderAndSnake {
    
    /**
     * The main method of the class which executes the play method of the LadderAndSnake class.
     *
     * @param args The command line arguments.
     * @throws InterruptedException Throws an exception if there is an interruption while waiting.
     */
    public static void main(String[] args) throws InterruptedException {

        Scanner keyIn = new Scanner(System.in);

        // Prompt user with welcome message and asks the number of players
        System.out.print("|=========================================================================================|");
        System.out.print("\n                               Welcome to Snake and Ladder!                              ");
        System.out.print("\n                                       by Eric Chen                                      ");
        System.out.print("\n                          Please enter the number of players: ");
        
        // Stores the number entered and verifies that the number is valid for this program
        int numPlayers = keyIn.nextInt();
        LadderAndSnake players = new LadderAndSnake(numPlayers);
        
        // Create an instance of the LadderAndSnake class
        LadderAndSnake playGame = new LadderAndSnake();

        // Call the play method of the LadderAndSnake instance
        playGame.play();
        

        keyIn.close();
    }

    

}
