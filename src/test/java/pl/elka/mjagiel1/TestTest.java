package pl.elka.mjagiel1;

import org.junit.Before;
import org.junit.Test;
import pl.elka.mjagiel1.extractor.Extractor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class TestTest {

  Extractor extractor;

  @Before
  public void setUp() throws Exception {
    extractor = new Extractor();
  }

  @Test
  public void name() {
    InputStream datasetInputStream = this.getClass().getResourceAsStream("/dataset.txt");

    try (Stream<String> stream = new BufferedReader(new InputStreamReader(datasetInputStream))
        .lines()) {
      stream.limit(10).forEach(line -> {
        System.out.print(line + "\t --- \t");
        System.out.println(extractor.extract(line));
      });
    }

  }
}
