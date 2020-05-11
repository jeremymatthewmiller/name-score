package name.score.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CharacterValueScoringServiceTest {

  private CharacterValueScoringService characterValueScoringService = new CharacterValueScoringService();

  @ParameterizedTest
  @MethodSource("exampleNamesLists")
  void calculateScoreShouldCalculateScore(final List<String> names, final int expectedValue) {
    final int result = characterValueScoringService.calculateScore(names);

    assertThat(result).isEqualTo(expectedValue);
  }

  static Stream<Arguments> exampleNamesLists() {
    return Stream.of(
        arguments(Arrays.asList(), 0),
        arguments(Arrays.asList("AAA"), 3),
        arguments(Arrays.asList("AAA", "LINDA", "Z"), 161),
        arguments(Arrays.asList("MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"), 3194)
    );
  }

  @ParameterizedTest
  @MethodSource("exampleNames")
  void calculateNameScoreShouldCalculateSumOfCharacters(final String name, final int expectedValue) {
    final long result = characterValueScoringService.calculateNameScore(name, 1);

    assertThat(result).isEqualTo(expectedValue);
  }

  static Stream<Arguments> exampleNames() {
    return Stream.of(
        arguments("A", 1),
        arguments("Z", 26),
        arguments("AAA", 3),
        arguments("LINDA", 40),
        arguments("JEREMY", 76),
        arguments("jeremy", 0),
        arguments("JER12345EMY", 76),
        arguments("JER&^%$#EMY", 76),
        arguments("JEREMY MILLER", 145)
    );
  }
}