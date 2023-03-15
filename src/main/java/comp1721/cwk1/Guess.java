package comp1721.cwk1;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Guess {
    // Use this to get player input in readFromPlayer()
    private static final Scanner INPUT = new Scanner(System.in);
    private final int guessNumber;
    private String s;
    private String chosenWord;

    // TODO: Implement constructor with int parameter
    public Guess(int num) {
        if (num < 1) {
            throw new GameException("Wrong!!");
        } else if (num > 6) {
            throw new GameException("Wrong!!");
        } else {
            guessNumber = num;
        }
        readFromPlayer();
        chosenWord = s.toUpperCase(Locale.ROOT);
    }

    // TODO: Implement constructor with int and String parameters
    public Guess(int num, String word) {
        if (num < 1) {
            throw new GameException("Wrong!!");
        } else if (num > 6) {
            throw new GameException("Wrong!!");
        } else {
            guessNumber = num;
        }
        if (word.length() != 5) {
            throw new GameException("Your word lenth is wrong!!");
        }
        for (int i = 0; i < 5; i++) {
            if (Character.isDigit(word.charAt(i))) {
                throw new GameException("Your word is wrong!It can not be digits!");
            }
        }
        chosenWord = word.toUpperCase(Locale.ROOT);
    }

    // TODO: Implement getGuessNumber(), returning an int
    public int getGuessNumber() {
        return guessNumber;
    }

    // TODO: Implement getChosenWord(), returning a String
    public String getChosenWord() {
        return chosenWord.toUpperCase(Locale.ROOT);
    }

    // TODO: Implement readFromPlayer()
    public void readFromPlayer() {
        s = INPUT.nextLine();
        if (s.length() != 5) {
            throw new GameException("Your word lenth is wrong!!");
        }
        for (int i = 0; i < 5; i++) {
            if (Character.isDigit(s.charAt(i))) {
                throw new GameException("Your word is wrong!It can not be digits!");
            }
        }
        chosenWord = s.toUpperCase(Locale.ROOT);
    }

    // TODO: Implement compareWith(), giving it a String parameter and String return type
    public String compareWith(String target) {
        StringBuilder ww;
        ww = new StringBuilder();
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == target.charAt(i)) {
                ww.append(String.format("\033[30;102m %c \033[0m", chosenWord.charAt(i)));
            } else {
                int a = 0;
                for (int b = 0; b < target.length(); b++) {
                    if (chosenWord.charAt(i) == target.charAt(b)) {
                        ww.append(String.format("\033[30;103m %c \033[0m", chosenWord.charAt(i)));
                        a = 1;
                        break;
                    }
                }
                if (a != 1) {
                    ww.append(String.format("\033[30;107m %c \033[0m", chosenWord.charAt(i)));
                }

            }
        }
        return ww.toString();
    }

    public String enabledcompareWith(String target) {
        StringBuilder gre;
        StringBuilder yel;
        StringBuilder green;
        gre = new StringBuilder();
        yel = new StringBuilder();
        ArrayList<String> gree = new ArrayList<>();
        ArrayList<String> yell = new ArrayList<>();
        green = new StringBuilder();

        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == target.charAt(i)) {
                gre.append(i);
            } else {
                for (int b = 0; b < target.length(); b++) {
                    if (chosenWord.charAt(i) == target.charAt(b)) {
                        yel.append(i);
                        break;
                    }
                }

            }
        }
        changenumberbe(gre, gree);
        changenumberbe(yel, yell);
        for (int i = 0; i < yell.size(); i++) {
            if (i == yell.size() - 1) {
                if (yell.size() > 1) {
                    green.append(" and ").append(yell.get(i)).append(" correct but in wrong place");
                } else {
                    green.append(yell.get(i)).append(" correct but in wrong place");
                    break;
                }
            } else if (i == yell.size() - 2) {
                green.append(yell.get(i));
            } else {
                green.append(yell.get(i)).append(", ");
            }
        }
        if (gree.size() > 0 && green.length() > 0) {
            green.append(", ");
        }
        for (int i = 0; i < gree.size(); i++) {
            if (i == gree.size() - 1) {
                if (gree.size() > 1) {
                    green.append(" and ").append(gree.get(i)).append(" perfect");
                } else {
                    green.append(gree.get(i)).append(" perfect");
                }

            } else if (i == gree.size() - 2) {
                green.append(gree.get(i));
            } else {
                green.append(gree.get(i)).append(", ");
            }
        }

        if (green.isEmpty()) {
            System.out.print("All wrong !");
        }
        return green.toString();
    }

    private void changenumberbe(StringBuilder gre, ArrayList<String> gree) {
        for (int gr = 0; gr < gre.length(); gr++) {
            if (gre.charAt(gr) == '0') {
                gree.add("1st");
            } else if (gre.charAt(gr) == '1') {
                gree.add("2nd");
            } else if (gre.charAt(gr) == '2') {
                gree.add("3rd");
            } else if (gre.charAt(gr) == '3') {
                gree.add("4st");
            } else if (gre.charAt(gr) == '4') {
                gree.add("5st");
            }

        }
    }

    // TODO: Implement matches(), giving it a String parameter and boolean return type
    public boolean matches(String target) {
        return target.equals(chosenWord);
    }

}
