package name.score.services;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public abstract class UploadService {
  private static final int QUOTE_CHARACTER = '\'';
  private static final int DOUBLE_QUOTE_CHARACTER = '"';

  abstract public Reader uploadRecords(final String path) throws IOException;

  /**
   * Parses the file contents into a List of names.
   *
   * @param reader A Reader representing the contents of a file with a list of names
   * @return A List of names
   * @throws IOException
   */
  public List<String> parseNameFile(final Reader reader) throws IOException {
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
}
