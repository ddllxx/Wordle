package comp1721.cwk1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Game {
    private StringBuilder d =new StringBuilder();
    private final int gameNumber;
    private final String target;


    // TODO: Implement constructor with String parameter
    public Game(String filename) throws IOException {
        WordList sc = new WordList(filename);
        int temp;
        temp = (int) (LocalDate.now().toEpochDay() - LocalDate.of(2021, 6, 19).toEpochDay());
        target = sc.getWord(temp);
        gameNumber = temp;
    }

    // TODO: Implement constructor with int and String parameters
    public Game(int num, String filename) throws IOException {
        WordList sc = new WordList(filename);
        gameNumber = num;
        target = sc.getWord(gameNumber);
    }

    // TODO: Implement play() method
    public void play() {
        int time = 0;
        int check = 0;
        for (int a = 0; a < 6; a++) {
            String dlx;
            System.out.printf("Enter guess (%d/6):", a + 1);
            Guess g = new Guess(a + 1);
            dlx = g.compareWith(target);
            d.append(dlx);
            d.append("\n");
            System.out.println(dlx);
            time++;
            if (g.matches(target)) {
                check = 1;
                if (time == 1) {
                    System.out.println("Superb - Got it in one!");
                    break;
                } else if (time > 1 && time <= 5) {
                    System.out.println("Well done!");
                    break;
                } else if (time == 6) {
                    System.out.println("That was a close call!");
                    break;
                }

            }
        }
        if (check == 0) {
            System.out.println("Nope - Better luck next time!");
            System.out.println("The right word is " + target);


        }

    }

    // TODO: Implement save() method, with a String parameter
    public void save(String filename) {
        File file = new File(filename);
        PrintWriter ttt = null;
        try {
            ttt = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert ttt != null;
        ttt.print(d);
        ttt.close();
    }


    public void enabledplay() {
        int check = 0;
        for (int a = 0; a < 6; a++) {
            String dlx;
            String lastname;
            System.out.printf("Enter guess (%d/6):", a + 1);
            Guess g = new Guess(a + 1);
            dlx = g.enabledcompareWith(target);
            lastname=g.getChosenWord();
            d.append(lastname);
            d.append("\n");
            if (!dlx.equals("1st, 2nd, 3rd, 4st and 5st perfect")){
                System.out.println(dlx);
            }

            if (g.matches(target)) {
                check = 1;
                System.out.println("You won!");
                break;
            }
        }
        if (check == 0) {
            System.out.println("Nope - Better luck next time!");
            System.out.println("The right word is " + target);
        }

    }
}
