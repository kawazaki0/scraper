package pl.elka.mjagiel1.extractor;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static pl.elka.mjagiel1.extractor.IngredientLabeler.Labels;

public class IngredientLabelerTest {

  private IngredientLabeler ingredientLabeler = new IngredientLabeler();

  @Test
  public void test() {
    assertLabel(
        Lists.newArrayList(Labels.BEGIN_QUANTITY, Labels.BEGIN_UNIT, Labels.BEGIN_INGREDIENT),
        "1 kg ziemniak");
    assertLabel(
        Lists.newArrayList(Labels.BEGIN_QUANTITY, Labels.BEGIN_UNIT, Labels.BEGIN_INGREDIENT),
        "5 łyżek ziemniaków");
    assertLabel(Lists
        .newArrayList(Labels.BEGIN_INGREDIENT, Labels.INSIDE_INGREDIENT, Labels.BEGIN_QUANTITY,
            Labels.BEGIN_UNIT), "przecier pomidorowy 500 ml");
    assertLabel(Lists.newArrayList(Labels.BEGIN_QUANTITY, Labels.BEGIN_INGREDIENT), "8 jajek");
    assertLabel(Lists.newArrayList(Labels.BEGIN_INGREDIENT), "jajka");
    assertLabel(Lists.newArrayList(Labels.BEGIN_UNIT, Labels.BEGIN_INGREDIENT), "kostka masła");
  }

  private void assertLabel(ArrayList expected, String source) {
    List<Labels> labels = ingredientLabeler.label(source);
    assertArrayEquals(expected.toArray(), labels.toArray());
  }

}
