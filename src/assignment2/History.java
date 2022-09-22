/* EE422C Assignment #2 submission by
 * <Jason Zubia>
 * <jgz279>
 */

package assignment2;

public class History
{
    String[] guessHistory;

    public History(int guessNumber)
    {
        guessHistory = new String[guessNumber];
    }

    public void updateHistory(String playerResponse, int numberOfBlackPegs, int numberOfWhitePegs, int index)
    {
        guessHistory[index] = playerResponse + "\t\t" + numberOfBlackPegs + "B_" +
                numberOfWhitePegs +"W";
    }

    public void printHistory(int counter)
    {
        for (int i = 0; i < counter; i++)
        {
            System.out.print("\n" + guessHistory[i]);
        }
    }
}
