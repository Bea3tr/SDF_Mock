package mock;

public class Recursive {

    public static void recursiveDescending (int n) {
        System.out.println(n);
        // Break condition
        if(n > 5)
            recursiveDescending(n-1);
    }

    public static void recursiveAscending (int n) {
        System.out.println(n);
        if(n < 5)
            recursiveAscending(n+1);
    }

    // Takes in odd and even array
    public static void minimax(int[] values, boolean isMax) {
        // Given an array of integers, find the optimum value
        int newSize = values.length - 1;
        // Break condition
        if (values.length == 1) 
            System.out.println("Optimum value: " + values[0]);
        else {
            int[] afterMini = new int[newSize];
            if (isMax) {
                // Compare & take max value
                for(int i = 0; i < newSize; i++) {
                    afterMini[i] = Math.max(values[i], values[i+1]);
                }
                minimax(afterMini, !isMax);
            }
            else {
                // Compare & take min value
                for(int i = 0; i < newSize; i++) {
                    afterMini[i] = Math.min(values[i], values[i+1]);
                }
                minimax(afterMini, !isMax);
            }
        }
    }

    public static int calculateDepth(int n) {
        if(n == 1)
            return 0;
        else
            return 1 + calculateDepth(n/2);
    }

    public static int minimaxCondensed(int[] values, boolean isMax, int currentIdx, int depth, int currentDepth) {
        if(currentDepth == depth)
            return values[currentIdx];
        else {
            if(isMax) {
                return Math.max(minimaxCondensed(values, !isMax, currentIdx*2, depth, currentDepth+1), 
                            minimaxCondensed(values, !isMax, currentIdx*2 + 1, depth, currentDepth+1));
            } else {
                return Math.min(minimaxCondensed(values, !isMax, currentIdx*2, depth, currentDepth+1), 
                            minimaxCondensed(values, !isMax, currentIdx*2 + 1, depth, currentDepth+1));
            }
        }
    }
    

    public static void main(String[] args) {
        // recursiveAscending(0);
        // System.out.println("===============================");
        // recursiveDescending(10);

        int[] values = {1, 2, 3, 4};
        minimax(values, true);
        int depth = calculateDepth(values.length);

        int value = minimaxCondensed(values, true, 0, depth, 0);
        System.out.println("Optimum value is: " + value);
    }
}
