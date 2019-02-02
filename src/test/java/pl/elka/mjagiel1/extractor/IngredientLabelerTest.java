package pl.elka.mjagiel1.extractor;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class IngredientLabelerTest {

  private IngredientLabeler ingredientLabeler = new IngredientLabeler();

  @Test
  public void test() {
    assertLabel(Lists.newArrayList("QUAN", "UNIT", "INGR"), "1 kg ziemniak");
    assertLabel(Lists.newArrayList("QUAN", "UNIT", "INGR"), "5 łyżek ziemniaków");
    assertLabel(Lists.newArrayList("INGR", "INGR", "QUAN", "UNIT"), "przecier pomidorowy 500 ml");
    assertLabel(Lists.newArrayList("QUAN", "INGR"), "8 jajek");
    assertLabel(Lists.newArrayList("INGR"), "jajka");
    assertLabel(Lists.newArrayList("UNIT", "INGR"), "kostka masła");
  }

  private void assertLabel(ArrayList expected, String source) {
    IngredientLabeler.IngredientLabel label = ingredientLabeler.label(source);
    assertArrayEquals(expected.toArray(), label.getLabels().toArray());
  }

}
