package mock;

import java.util.*;

public class Permutations {

    private static Set<String> perm = new HashSet<>();

    public static void permutation(String remaining, String output) {
        if(remaining.length() == 0) 
            perm.add(output);

        for(int i = 0; i < remaining.length(); i++) {
            // for each character swap position of the remaining
            String currentCh = Character.toString(remaining.charAt(i));
            String removeCurrent = remaining.substring(0, i) + remaining.substring(i+1);
            permutation(removeCurrent, output+currentCh);
        }
    }

    public static void main(String[] args) {
        // Find number of permutations
        // Put permutations into Set collection 
        // Print out Set collection
        if(args.length == 0) {
            System.err.println("Missing input");
            System.exit(1);
        }
        String input = args[0];
        permutation(input, "");
        int numPerm = perm.size();
        System.out.println("Number of permutations: " + numPerm);
        perm.forEach(System.out::println);

    }
}
    

 