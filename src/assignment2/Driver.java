/* EE422C Assignment #2 submission by
 * <Jason Zubia>
 * <jgz279>
 */

package assignment2;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        // Scans input from keyboard
        Scanner input = new Scanner(System.in);

        // Creates the validator, validates user input
        Validator validator = new Validator(false);

        // Specifies if testingMode is enabled
        boolean testingMode = false;

        // Checks the CLA for testing mode
        if(args.length != 0)
        {
            testingMode = args[0].equals("1");
        }

        // Create a game
        Game masterMind = new Game(testingMode, input, validator);

        // Dialogue class will display the game intro
        Dialogue.initialGreeting();

        // Gets player response and saves it.
        String playGame = input.next();

        // If player is not ready, terminate the game
        while(validator.validateYes(playGame))
        {
            // Main game engine, runs the game
            masterMind.runGame();

            // Prompt the user if they would like to play again
            Dialogue.playAgain();

            // Ask for user input
            playGame = input.next();
        }
    }
}
