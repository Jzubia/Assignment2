package assignment2;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        // Scans input from keyboard
        Scanner input = new Scanner(System.in);

        boolean testingMode = true;

        // Checks the CLA for testing mode
        if(args.length != 0)
        {
            testingMode = args[0].equals("1");
        }

        // Create a game
        Game masterMind = new Game(testingMode, input);

        // Main game engine, runs the game
        masterMind.runGame();

    }




}
