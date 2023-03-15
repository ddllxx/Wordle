package comp1721.cwk1;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {
    // TODO: Implement constructor with a String parameter
    private List<String> words = new ArrayList<>();

    public WordList(String filename) throws IOException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String word = scanner.nextLine();
            words.add(word);
        }
    }

    // TODO: Implement size() method, returning an int
    public int size() {
        return words.size();
    }

    // TODO: Implement getWord() with an int parameter, returning a String
    public String getWord(int n) throws GameException {
        if (n < 0) {
            throw new GameException("Get the word wrong!");
        } else if (n >= words.size()) {
            throw new GameException("Get the word wrong!");
        } else {
            return words.get(n);
        }
    }
}




