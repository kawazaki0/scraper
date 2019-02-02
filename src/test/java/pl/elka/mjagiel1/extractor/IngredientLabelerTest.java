package pl.elka.mjagiel1.extractor;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static pl.elka.mjagiel1.extractor.IngredientLabeler.Labels;

public class IngredientLabelerTest {

  private IngredientLabeler ingredientLabeler = new IngredientLabeler();

  @Test
  public void test() {
    assertLabel(Lists.newArrayList(Labels.BEGIN_QUANTITY, Labels.BEGIN_UNIT, Labels.BEGIN_INGREDIENT), "1 kg ziemniak");
    assertLabel(Lists.newArrayList("B-QUAN", "B-UNIT", "B-INGR"), "5 łyżek ziemniaków");
    assertLabel(Lists.newArrayList("B-INGR", "I-INGR", "B-QUAN", "B-UNIT"), "przecier pomidorowy 500 ml");
    assertLabel(Lists.newArrayList("B-QUAN", "B-INGR"), "8 jajek");
    assertLabel(Lists.newArrayList("B-INGR"), "jajka");
    assertLabel(Lists.newArrayList("B-UNIT", "B-INGR"), "kostka masła");
  }

  private void assertLabel(ArrayList expected, String source) {
    IngredientLabeler.IngredientLabel label = ingredientLabeler.label(source);
    assertArrayEquals(expected.toArray(), label.getLabels().toArray());
  }

}
