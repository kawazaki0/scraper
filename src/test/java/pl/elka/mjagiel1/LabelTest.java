package pl.elka.mjagiel1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.elka.mjagiel1.extractor.Ingredient;
import pl.elka.mjagiel1.extractor.IngredientExtractor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.stream.Stream;

@Ignore
public class LabelTest {

  IngredientExtractor extractor;

  @Before
  public void setUp() throws Exception {
    extractor = new IngredientExtractor();
  }

  @Test
  public void name() throws FileNotFoundException {
    InputStream datasetInputStream = this.getClass().getResourceAsStream("/dataset.txt");
    OutputStream labeledList = new FileOutputStream("labeled.txt");
    try (Stream<String> stream = new BufferedReader(new InputStreamReader(datasetInputStream))
        .lines()) {
      stream.limit(100).forEach(line -> {
        try {
          Ingredient ingredient = extractor.extract(line);

          String quantity = ingredient.getUnit().getQuantity();
          String unitType = ingredient.getUnit().getUnitType().getRawType();
          String ingredientName = ingredient.getName();

          labeledList.write(quantity.getBytes());
          System.out.print(line + "\t --- \t");
          System.out.println();
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }

  }
}
