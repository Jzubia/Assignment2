package assignment2;

import java.util.Scanner;

public class Game
{
    // Specifies if secret code should be shown
    boolean gameMode;
    Scanner playerInput;

    // Constructor
    public Game(boolean testingMode, Scanner input)
    {
        gameMode = testingMode;
        playerInput = input;
    }

    public void runGame()
    {
        // Saving gameConfig Guesses to local var
        int numberOfGuesses = GameConfiguration.guessNumber;

        // Indicates if player input was valid
        boolean validGuess = true;

        // Dialogue class will display the game intro
        Dialogue.initialGreeting();

        // Gets player response and saves it.
        String playerResponse = playerInput.next();

        // If player is not ready, terminate the game
        if(playerResponse.equals("N") || !playerResponse.equals("Y")) // TODO verify user input is Y or N
        {
            System.exit(0);
        }

        // Generates secretCode
        String secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();

        // Generates secret code line with/without secret code depending on game mode
        Dialogue.generatingSecretCode(gameMode, secretCode);

        // Start of game begin to prompt the user for their guess
        Dialogue.guessesLeft(numberOfGuesses);

        while(numberOfGuesses != 0)
        {
            // Gets player response and saves it.
            playerResponse = playerInput.next();


            Validator validator = new Validator(playerResponse);

            // Validates user input
            validGuess = validator.validatePlayerGuess(playerResponse);

            if(validGuess)
            {
                Dialogue.validFeedback(playerResponse);
                numberOfGuesses--;
                Dialogue.guessesLeft(numberOfGuesses);
            }
            else
            {
                Dialogue.invalidFeedback(playerResponse);
                Dialogue.invalidGuess();
            }
        }
    }
}
