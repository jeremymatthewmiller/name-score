package name.score.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class UploadServiceTest {

  UploadService uploadService = new UploadService() {
    @Override
    public Reader uploadRecords(String path) throws IOException {
      return null;
    }
  };

  @Test
  void parseNameFileShouldReturnNamesFromFileAsListOfNames() {
    Reader reader = new StringReader("\"JONATHAN\",\"JORDAN\",\"DONNIE\",\"DANNY\",\"JOEY\"");

    try {
      final List<String> result = uploadService.parseNameFile(reader);

      assertThat(result).containsExactly("JONATHAN", "JORDAN", "DONNIE", "DANNY", "JOEY");
    } catch (IOException e) {
      fail("parseNameFileShouldReturnNamesFromFileAsListOfNames failed to read the file contents");
    }
  }

  @Test
  void parseNameFileShouldReturnEmptyNameListWhenFileIsEmpty() {
    Reader reader = new StringReader("");

    try {
      final List<String> result = uploadService.parseNameFile(reader);

      assertThat(result).isEmpty();
    } catch (IOException e) {
      fail("parseNameFileShouldReturnNamesFromFileAsListOfNames failed to read the file contents");
    }
  }
}