package randnum;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class HangMan {

    public void hangman(String file) {
        try{
            // Read file to get words
            List<String> wordList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while(line != null) {
                line = br.readLine();
                if(line == null)
                    break;
                wordList.add(line);
            }
            br.close();

            Random rand = new SecureRandom();
            int idx = rand.nextInt(wordList.size());
            
            String word = wordList.get(idx);
            Console cons = System.console();
            char[] guess = new char[word.length()];

            while(!new String(guess).equals(word)) {
                String ch = cons.readLine("Input: ").toLowerCase();
                for (int i = 0; i < word.length(); i++) {
                    if(ch.charAt(0) == word.charAt(i)) {
                        guess[i] = ch.charAt(0);
                    } else if(guess[i] == '\u0000') {
                        guess[i] = '_';
                    }
                }
                System.out.println(new String(guess));
            }
            System.out.println("You've guessed the word!\nWord: " + word);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
