/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package name.score;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class NameScore {

    private static final String DEFAULT_FILE_PATH = "src/main/resources/names.txt";
    private static final int QUOTE_CHARACTER = '\'';
    private static final int DOUBLE_QUOTE_CHARACTER = '"';
    private static final int CHARACTER_OFFSET = 64;

    public static void main(String[] args) {
        NameScore nameScore = new NameScore();

        try {
            Reader fileReader = nameScore.getFileFromCommandLineArguments(args);
            List<String> names = nameScore.parseNameFile(fileReader);
            int score = nameScore.calculateTotalScore(names);
            System.out.println("Score: " + score);
        } catch (IOException e) {
            System.out.println("An error occurred while calculating the name score. Exiting...");
        }
    }

    /**
     * Find and open a file given in the first position of the commandLineArgs array.
     *
     * @param commandLineArgs An array with a full path in the 0 element of the array
     * @return A reader with the file's contents
     * @throws IOException If the given filename cannot be found
     */
    Reader getFileFromCommandLineArguments(String[] commandLineArgs) throws IOException {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(commandLineArgs.length > 0 ? commandLineArgs[0] : DEFAULT_FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + commandLineArgs[0]);
            throw e;
        }

        return fileReader;
    }

    /**
     * Parses the file contents into a List of names.
     *
     * @param reader A Reader representing the contents of a file with a list of names
     * @return A List of names
     * @throws IOException
     */
    List<String> parseNameFile(Reader reader) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        List<String> tokens = new ArrayList<>();

        int currentToken = streamTokenizer.nextToken();
        while (currentToken != StreamTokenizer.TT_EOF) {

            if (streamTokenizer.ttype == StreamTokenizer.TT_WORD
                || streamTokenizer.ttype == QUOTE_CHARACTER
                || streamTokenizer.ttype == DOUBLE_QUOTE_CHARACTER) {

                tokens.add(streamTokenizer.sval.toUpperCase());
            }
            currentToken = streamTokenizer.nextToken();
        }

        return tokens;
    }

    /**
     * Calculates the score for all names in the list and returns the total sum
     *
     * @param names A mutable List of names. The names List will be sorted alphabetically.
     * @return The sum of all name scores in the names List
     */
    int calculateTotalScore(List<String> names) {
        Collections.sort(names);
        String[] namesArray = names.toArray(new String[0]);

        int sum = IntStream.range(0, names.size())
            .mapToObj(i -> calculateNameScore(namesArray[i], i+1))
            .mapToInt(i -> i.intValue())
            .sum();

        return sum;
    }

    /**
     * Calculates the score of name based on the character values.
     *
     * name.chars() allows access to the unicode value of each character in the String
     * Latin Alphabet Uppercase 'A' == U+0041 -> which is represented by the decimal value 65
     * Latin Alphabet Uppercase 'Z' == U+005A -> which is represented by the decimal value 90
     *
     * To calculate the score of each character in the given name, in the map function
     * subtract the unicode value by 64 (CHARACTER_OFFSET). Then sum each value for each
     * character for the result. Only uppercase Latin Unicode characters are counted in the score.
     *
     * @param name
     * @return The sum of all characters in the name multiplied by it's position modifier
     */
    int calculateNameScore(final String name, int position) {
        int sum = name.chars().map(c -> c >= 65 && c <= 90 ? c - CHARACTER_OFFSET : 0).sum();
        int score = sum * position;

        return score;
    }
}