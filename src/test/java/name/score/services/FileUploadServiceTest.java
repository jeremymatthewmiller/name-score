package name.score.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileUploadServiceTest {

  private static final String DEFAULT_TEST_FILE_PATH = "src/test/resources/names.txt";

  private FileUploadService fileUploadService = new FileUploadService();

  @Test
  void uploadRecordsShouldGetFileInCommandLineArgsIfExists() {
    String path = DEFAULT_TEST_FILE_PATH;

    try {
      final Reader result = fileUploadService.uploadRecords(path);

      assertThat(result).isNotNull();
    } catch (IOException e) {
      fail("getFileFromCommandLineArgumentsShouldGetFileInCommandLineArgsIfExists failed to create file from command line arguments");
    }
  }

  @Test
  void uploadRecordsShouldUseDefaultFileIfNoPathIsGiven() {
    String path = "";

    try {
      final Reader result = fileUploadService.uploadRecords(path);

      assertThat(result).isNotNull();
    } catch (IOException e) {
      fail("getFileFromCommandLineArgumentsShouldGetFileInCommandLineArgsIfExists failed to create file from command line arguments");
    }
  }

  @Test
  void uploadRecordsShouldThrowExceptionWhenFileNotFound() {
    String path = "/this/does/not/exist.txt";

    assertThrows(IOException.class, () -> {
      fileUploadService.uploadRecords(path);
    });
  }
}