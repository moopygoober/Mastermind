/*	Name: Mark Higley
	Problem:  Create Mastermind tester?
	Pseudocode: Output message to the screen
	Notes:.
	Maintenance log:
        Date:		Done:
        3/30/26     Added an NPC class
*/


import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileReader;

public class Mastermind {

    private static final String testFilename = "C:/Users/cupan/IdeaProjects/Mastermind/src/mastermind_tester.txt";

    static void main(String[] args) throws FileNotFoundException {
        //Scanner file = new Scanner(new File("C:/Users/cupan/IdeaProjects/Mastermind/src/mastermind_4p6c.txt"));
        FileReader fr = new FileReader(testFilename);
        //Scanner file = new Scanner(fr);
        Scanner input = new Scanner(System.in);

        //file.nextLine(); // Drop the header row
        String secret = "";
        String guess = "";

        runTest();
    }

    private static int[] runTest() throws FileNotFoundException {
//        byte[] secretDigits = codeStringToBytes(secret);
//        byte[] guessDigits = codeStringToBytes(guess);

        int b = 0;
        int w = 0;
        Random rn = new Random();

        Scanner input = new Scanner(System.in);
        FileReader fr = new FileReader(testFilename);
        Scanner lineScanner = new Scanner(fr);
        lineScanner.nextLine();

        List<String> secretNum = new ArrayList<>();
        List<String> guessNum = new ArrayList<>();
        while (lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();
            String[] testData = line.split(",");
            secretNum.add(testData[0]);
            guessNum.add(testData[1]);
        }
        int Ran_num = rn.nextInt(secretNum.size());

        String sN = secretNum.get(Ran_num);
        System.out.println(secretNum.getFirst());
        List<String> userGuess = new ArrayList<>();

        String guess = input.next();
        userGuess.add(guess);

        // For-loop and an if statement to determine what number is correct and in the correct spot (b)
        // and what number is correct but in the wrong spot (w)
        System.out.println("right in right spot: " + b + " right in wrong spot: " + w);
        return new int[] {b,w};
        //System.out.println(Arrays.toString(hints));
        //System.out.println("\nRight number in right spot: " + b + "\nRight number in wrong spot: " + w);


    }


    public static byte[] codeStringToBytes(String s) {
        byte[] r = s.getBytes();
        for (int i = 0; i < r.length; i++) {
            r[i] -= '0';
        }
        return r;
    }


}