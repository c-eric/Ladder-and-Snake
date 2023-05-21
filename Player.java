/**
@author Eric Chen
@version 1
*/

/** 
 * The `Player` class is used to represent a player in the game of LadderAndSnake.
 * It contains information about a player's name, start position, end position, and the positions of the ladders and snakes on the game board.
 * The class also provides methods for checking player collisions, ladders, snakes, and if the player's end position exceeds 100.
 */
public class Player {

    // Instance variables
    private String name;
    private int start_pos = 0;
    private int end_pos = 0;
    private static int[][] ladders = {{1, 38}, {4, 14}, {9, 31}, {21, 42}, {28, 84}, {51, 67}, {71, 91}, {80, 100}};
    private static int[][] snakes = {{98, 78}, {95, 75}, {93, 73}, {87, 24}, {64, 60}, {62, 19}, {56, 53}, {49, 11}};

    /** 
     * Constructor for creating a player object.
     * 
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /** 
     * Constructor for creating a player object with start and end position.
     * 
     * @param start_pos the start position of the player
     * @param end_pos the end position of the player
     */
    public Player(int start_pos, int end_pos) {
        this.start_pos = start_pos;
        this.end_pos = end_pos;
    }
    
    /** 
     * Getter method for the start position of the player.
     * 
     * @return int the start position of the player
     */
    public int getStartPos() {
        return this.start_pos;
    }

    
    /** 
     * Setter method for the start position of the player.
     * 
     * @param start_pos the new start position of the player
     */
    public void setStartPos(int start_pos) {
        this.start_pos = start_pos;
    }

    
    /** 
     * Getter method for the end position of the player.
     * 
     * @return int the end position of the player
     */
    public int getEndPos() {
        return this.end_pos;
    }

    
    /** 
     * Setter method for the end position of the player.
     * 
     * @param end_pos the new end position of the player
     */
    public void setEndPos(int end_pos) {
        this.end_pos = end_pos;
    }

    
    /** 
     * Getter method for the name of the player.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /**
     * Check if the current player's end position is equal to another player's end position
     * If true, reset the other player's start position to 0 and print a message indicating this
     * @param otherPlayer The other player to check collision with
     */
    public void checkPlayerCollision(Player otherPlayer) {
        if (this.end_pos == otherPlayer.getEndPos()) {
            System.out.println("/!\\ " + this.name + " kicked " + otherPlayer.getName() + " and reset them to 0 /!\\");
            otherPlayer.setStartPos(0);
        }
    }
    
    /**
     * Check if the current player's end position is equal to the start position of a ladder
     * If true, update the player's start position to the end position of the ladder and print a message indicating this
     */
    public void checkLadders() {
        for (int i = 0; i < ladders.length; i++) {
            if (this.end_pos == ladders[i][0]) {
                System.out.println("Player " + this.name + " encountered a ladder and climbed from " + ladders[i][0] + " to " + ladders[i][1]);
                this.start_pos = ladders[i][1];
            }
        }
    }

    /**
     * Check if the current player's end position is equal to the start position of a snake
     * If true, update the player's start position to the end position of the snake and print a message indicating this
     */
    public void checkSnakes() {
        for (int i = 0; i < snakes.length; i++) {
            if (this.end_pos == snakes[i][0]) {
                System.out.println("Player " + this.name + " encountered a snake and was swallowed from " + snakes[i][0] + " to " + snakes[i][1]);
                this.start_pos = snakes[i][1];
            }
        }
    }

    /**
     * Check if the current player's end position is greater than 100
     * If true, move the player back to 100 minus the difference between their end position and 100
     */
    public void exceedHundred() {
        int exceedNum = this.end_pos-100;
        if (this.end_pos > 100) {
            this.start_pos = 100 - (exceedNum);
            System.out.println("Player " + this.name + " exceeded 100 by " + exceedNum + ", they will be moved back to " + this.start_pos);
        } 

        
    }
}
