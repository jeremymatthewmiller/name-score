package name.score.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class CharacterValueScoringService implements ScoringService {

  private static final int CHARACTER_OFFSET = 64;

  /**
   * Calculates the score for all names in the list and returns the total sum
   *
   * @param names A mutable List of names. The names List will be sorted alphabetically.
   * @return The sum of all name scores in the names List
   */
  @Override
  public int calculateScore(List<String> names) {
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
   * Any character outside the range A (65) through Z (90) inclusive will not be included in the score.
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
