package mock;

import java.io.Console;
import java.security.SecureRandom;
import java.util.Random;;

public class MasterMind {

    public static int generateDigits() {
        Random rand = new SecureRandom();

        String output = "";
        for (int i = 0; i < 4; i++) {
            int digit = rand.nextInt(6) + 1;
            output += Integer.toString(digit);
        }
        return Integer.parseInt(output);
    }

    public static void playGame() {
        Console cons = System.console();
        int tries = 12;
        boolean isWon = false;
        int random = generateDigits();
        String toCheck = String.valueOf(random);

        while (!isWon && tries > 0) {
            int correctPos = 0;
            int wrongPos = 0;
            String fromUser = cons.readLine("Please enter 4 digits: ");
            try {
                if (Integer.parseInt(fromUser) < 1111 || Integer.parseInt(fromUser) > 6666) {
                    System.out.println("===Invalid input! Please enter a number between [1111 - 6666]===\n");
                    continue;
                } else if (fromUser.length() > 4) {
                    System.out.println("===Invalid input! Please enter 4 digits only===");
                    continue;
                }
            } catch (NumberFormatException ex) {
                System.out.println("===Invalid input! Please enter digits only===\n");
                continue;
            }
            // Check if number matches
            System.out.println("Number: " + toCheck);

            for (int i = 0; i < fromUser.length(); i++) {
                if (fromUser.charAt(i) == toCheck.charAt(i))
                    correctPos += 1;
                else if (toCheck.contains(Character.toString(fromUser.charAt(i))))
                    wrongPos += 1;
            }
            if (correctPos == 4) {
                isWon = true;
                System.out.println("Your guess is correct! The number is: " + toCheck);
            } else {
                tries--;
                System.out.println("# in correct position: " + correctPos);
                System.out.println("# in wrong position: " + wrongPos);
                System.out.println("Incorrect guess. Number of tries left: " + tries);
            }
        }
        if(tries == 0)
            System.out.println("You have ran out of tries...");
        String input = cons.readLine("Would you like to play again? (y/n): ");
        if (input.equals("y"))
            playGame();
    }

    public static void main(String[] args) {
        playGame();
    }
}
