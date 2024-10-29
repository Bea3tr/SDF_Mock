package randnum;

public class Main {
    public static void main(String[] args) {
        // MasterMind mm = new MasterMind();
        // mm.playGame();

        // NumGen ng = new NumGen();
        // ng.task9();
        // ng.task10();

        // Task 13
        String file = "";
        if(args.length > 0) 
            file = args[0];
        HangMan hm = new HangMan();
        hm.hangman(file);
    }
}
