package name.score.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileUploadService extends UploadService {

  private static final String DEFAULT_FILE_PATH = "src/main/resources/names.txt";

  /**
   * Find and open a file given in the first position of the commandLineArgs array.
   *
   * @param path An array with a full path in the 0 element of the array
   * @return A reader with the file's contents
   * @throws IOException If the given filename cannot be found
   */
  public Reader uploadRecords(final String path) throws IOException {
    FileReader fileReader = null;

    try {
      fileReader = new FileReader(!path.isBlank() ? path : DEFAULT_FILE_PATH);
    } catch (FileNotFoundException e) {
      System.out.println("Could not find " + path);
      throw e;
    }

    return fileReader;
  }
}
