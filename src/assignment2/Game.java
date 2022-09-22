/* EE422C Assignment #2 submission by
 * <Jason Zubia>
 * <jgz279>
 */

package assignment2;

import java.util.Scanner;

public class Game
{
    // Specifies if secret code should be shown
    boolean gameMode;
    Scanner playerInput;
    Validator validator;

    // Constructor
    public Game(boolean testingMode, Scanner input, Validator validator)
    {
        gameMode = testingMode;
        playerInput = input;
        this.validator = validator;
    }

    public void runGame()
    {

        // Generates secretCode
        String secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();

        // Creates a gameBoard state
        BoardState gameBoard = new BoardState("", 0,
                0, secretCode, GameConfiguration.guessNumber);

        // Creates Game History
        History gameHistory = new History(GameConfiguration.guessNumber);

        // Generates secret code line with/without secret code depending on game mode
        Dialogue.generatingSecretCode(gameMode, secretCode);

        // Start of game begin to prompt the user for their guess
        Dialogue.firstGuess();

        int counter = 0;

        while(gameBoard.getNumberOfGuessesRemaining() > 0)
        {
            // Gets player response and saves it.
            gameBoard.playerResponse = playerInput.next();

            if(validator.validateHistory(gameBoard.playerResponse))
            {
                gameHistory.printHistory(counter);
                Dialogue.guessesLeft(gameBoard.getNumberOfGuessesRemaining());
                gameBoard.playerResponse = playerInput.next();
            }

            // Validates user input
            validator.validatePlayerGuess(gameBoard.playerResponse);

            if(validator.getValidGuess())
            {
                if(gameBoard.playerWin())
                {
                    Dialogue.validFeedback(gameBoard);
                    Dialogue.youWin();
                    break;
                }

                Dialogue.validFeedback(gameBoard);

                gameHistory.updateHistory(gameBoard.playerResponse, gameBoard.getNumberOfBlackPegs(),
                        gameBoard.getNumberOfWhitePegs(), counter);
                counter++;
                gameBoard.updateNumberOfGuessesRemaining();

                if(gameBoard.getNumberOfGuessesRemaining() == 0)
                {
                    Dialogue.youLose();
                    break;
                }

                Dialogue.guessesLeft(gameBoard.getNumberOfGuessesRemaining());
            }
            else
            {
                Dialogue.invalidFeedback(gameBoard);
                Dialogue.invalidGuess();
            }
        }
    }
}
