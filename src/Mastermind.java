/*	Name: Mark Higley
	Problem:  Create Mastermind game
	Pseudocode: Got a random 4-digit number, each digit is 1-6. Had the user guess a legal 4-digit number
	and had the computer test if the secret number and the user guess is equal, or contains that digit somewhere in the
	secret number. Output number of black pins (right number, right location) and white pins (right number, wrong location).
	Notes: This took far too long. And I do not use the original file with each secret combination, guess combination, and the
	number of black and white pins for each combination. The file I use is a modified version where it is just the 1296 combinations of 1111
	through 6666
	Maintenance log:
        Date:		Done:
        4/3/26      Completed the program
*/


import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;


public class Mastermind {
    static void main(String[] args) throws FileNotFoundException {
        //File contains numbers 1111 - 6666 which is 1,296 lines
        Scanner file = new Scanner(new File("C:/Users/cupan/IdeaProjects/Mastermind-Program/src/mastermind_number_combo.txt"));
        boolean again = false;
        Random rn = new Random();
        Scanner input = new Scanner(System.in);
        Scanner answer = new Scanner(System.in);
        List<String> gameNum = new ArrayList<>();

        while (file.hasNext()){
            gameNum.add(file.nextLine());
        }

        do {
            //amountGuessed used to track the number of guesses from the user. Program stops at 9
            int amountGuessed = 1;
            int Ran_num = rn.nextInt(gameNum.size());
            //String arrays for both the user's guess and the secret number (chosen at random)
            //String arrays are used for the comparison at the bottom of program
            String[] guess = new String[4];
            String[] secret = new String[4];
            String secretNum = gameNum.get(Ran_num);

            for (int i = 0; i < 50; i++) {
                System.out.println();
            }

            System.out.println("Welcome to Mastermind! The computer will pick a random number and it's up to you to guess what number it is! " +
                    "\nAfter each guess you will receive a hint for which number is correct, and in the correct spot" +
                    "\nand which number is correct, but in the wrong spot.");
            System.out.println("Your guess here (Hit enter to lock in your guess!):");

            do {
                int blackPin = 0;
                int whitePin = 0;

                boolean cont = false;
                do {
                    String response = input.next();
                    if (response.contains("7") || response.contains("8") || response.contains("9") || response.contains("0") || response.length() != 4) {
                        System.out.println("That is not a valid guess!");
                        cont = false;
                    }
                    else{
                        //if user input has the legal numbers and is the legal length, it will add the input to the guess array
                        for (int i = 0; i < response.length(); i++) {
                            guess[i] = String.valueOf(response.charAt(i));
                        }
                        cont = true;
                    }
                    // this will run until the user input is valid
                }while(!cont);

                //the secret array is populated with the individual characters of the randomly selected secret number
                for (int i = 0; i < secretNum.length(); i++) {
                    secret[i] = String.valueOf(secretNum.charAt(i));
                }

                /*secret[0] = "6";
                secret[1] = "6";
                secret[2] = "6";
                secret[3] = "3";*/

                System.out.println("\n\n\n\n\n\nSecret Number: " + Arrays.toString(secret) + "\nYour Guess:    " + Arrays.toString(guess));

                //Another array is created to store a 2 if the secret at location 0-3 and guess at location 0-3 contains the same number
                //and, it stores a 1 if the guess at location 0-3 contains the same number at the previously chosen secret location 0-3
                //for example: guess at location 2 contains the same as secret at location 1
                int[] guessResult = new int[4];

                for (int i = 0; i < secret.length; i++) {

                    for (int n = 0; n < guess.length; n++) {
                        if (guessResult[n] == 0) {
                            if (guess[n].equals(secret[i])) {
                                guessResult[n] = 1;
                                break;
                            }

                        }
                    }
                    if (secret[i].equals(guess[i])) {
                        guessResult[i] = 2;
                    }

                        System.out.println("Itr " + i + ": " + Arrays.toString(guessResult));
                }
                System.out.println();
                //this gives the correct number of black and white pins for the user
                for (int i = 0; i < guessResult.length; i++) {
                    if (guessResult[i] == 2) {
                        blackPin++;
                    } else if (guessResult[i] == 1) {
                        whitePin++;
                    }
                }
                //System.out.println(Arrays.toString(guessResult));
                System.out.println("Right number in right spot: " + blackPin + " Right number in wrong spot: " + whitePin + "\nAttempt: " + amountGuessed);
                amountGuessed++;
                if (amountGuessed == 9) {
                    System.out.println("Be careful! This is your last guess!");
                }

            } while (!Arrays.equals(guess, secret) && amountGuessed < 10);

            if (Arrays.equals(guess, secret)) {
                System.out.println("Congratulations! You found the secret number! Play again?\n1. Yes\n2. No");
            } else {
                System.out.println("Aww...you didn't guess the number. It was " + Arrays.toString(secret) + " Wanna play again?\n1. Yes\n2. No");
            }

            int ans = answer.nextInt();
            again = ans == 1;

        } while (again);
        System.out.println("Bye!");
    }



}