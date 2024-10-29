package randnum;

import java.util.*;
import java.security.SecureRandom;
import java.io.Console;
import java.util.stream.*;

public class NumGen {

    Random rand = new SecureRandom();

    public void task9() {
        // Generate 6 digits random number [100000 - 999999]
        String input = "";
        while (!input.equals("quit")) {
            int generated = rand.nextInt(900000) + 100000;

            // Game start
            Console cons = System.console();
            int guess = 0;
            while (guess != generated) {
                input = cons.readLine("Guess the 6 digit number: ");
                if (input.equals("quit"))
                    break;

                try {
                    guess = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    System.out.println("===Invalid input! Please enter digits only===\n");
                    continue;
                }

                if (guess > 999999 || guess < 100000) {
                    System.out.println("===Invalid input! Please enter a 6 digits number===\n");
                    continue;
                }

                if (guess < generated)
                    System.out.println("Higher");
                if (guess > generated)
                    System.out.println("Lower");
            }
            if (guess == generated)
                System.out.println("You've guessed the right number!\nNumber is: " + generated);
        }
    }

    public void task10() {
        List<Integer> numbers = new ArrayList<>();
        Console cons = System.console();
        int[] guessed = new int[10];
        for(int i = 0; i < 10; i++) {
            int generated = rand.nextInt(100) + 1;
            numbers.add(generated);
        }
        Collections.shuffle(numbers);
        for (int j = 0; j < numbers.size(); j++) {
            int currNum = numbers.get(j);
            System.out.println(j+1 + ". " +  currNum);
            guessed[j] = numbers.get(j);
            List<Integer> greater = numbers.stream()  
                                    .filter(n -> n > currNum)
                                    .collect(Collectors.toList());
            List<Integer> lowerOrEqual = numbers.stream()
                                            .filter(n -> n <= currNum)
                                            .collect(Collectors.toList());
            System.out.println("Numbers more than shuffled: " + greater.size());
            System.out.println("Numbers less than or equals to shuffled: " + (lowerOrEqual.size() - 1));
            if(j < 9) 
                cons.readLine("Guess if the next number is higher or lower: ");
        }
    }
}
