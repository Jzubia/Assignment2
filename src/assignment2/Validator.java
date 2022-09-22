package assignment2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    String playerResponse;

    public Validator(String playerResponse)
    {
        this.playerResponse = playerResponse;
    }

    public boolean validatePlayerGuess(String playerResponse)
    {
        return !checkCasing() && checkGuessLength() && checkGuessColors();
    }

    private boolean checkCasing()
    {
        // Regex pattern to check if any input is lower-case
        Pattern lowerCase = Pattern.compile("[a-z]");

        Matcher matcher = lowerCase.matcher(playerResponse);

        // False if invalid guess, True if valid guess
        return matcher.find();
    }
    private boolean checkGuessLength()
    {
        return playerResponse.length() == GameConfiguration.pegNumber;
    }

    private boolean checkGuessColors()
    {
        String pegColors = String.join("", GameConfiguration.colors);
        Pattern colors = Pattern.compile("^[" + pegColors + "]{" + GameConfiguration.pegNumber + "}$");

        Matcher matcher = colors.matcher(playerResponse);

        return matcher.find();
    }
}
