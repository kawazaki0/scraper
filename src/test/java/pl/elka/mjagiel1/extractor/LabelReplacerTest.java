package pl.elka.mjagiel1.extractor;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import pl.elka.mjagiel1.extractor.IngredientLabeler.Labels;
import pl.elka.mjagiel1.extractor.unit.Unit;

import java.util.ArrayList;
import java.util.List;

import static pl.elka.mjagiel1.extractor.IngredientLabeler.Labels.BEGIN_INGREDIENT;
import static pl.elka.mjagiel1.extractor.IngredientLabeler.Labels.BEGIN_QUANTITY;
import static pl.elka.mjagiel1.extractor.IngredientLabeler.Labels.BEGIN_UNIT;
import static pl.elka.mjagiel1.extractor.IngredientLabeler.Labels.INSIDE_INGREDIENT;
import static pl.elka.mjagiel1.extractor.IngredientLabeler.Labels.OUTSIDE;

public class LabelReplacerTest {

  private LabelReplacer labelReplacer = new LabelReplacer();

  @Test
  public void replaceTest() {
    String unitTypeStub = "unitTypeExample";
    String unitStub = "unitExample";
    assertReplace(Lists.newArrayList(BEGIN_INGREDIENT, INSIDE_INGREDIENT),
        "przecieru pomidorowego", "przecieru pomidorowego", unitStub, unitTypeStub);
    assertReplace(Lists.newArrayList(BEGIN_INGREDIENT),
        "sól", "sól", unitStub, unitTypeStub);
    assertReplace(Lists.newArrayList(BEGIN_INGREDIENT, OUTSIDE),
        "sól xaxa", "sól", unitStub, unitTypeStub);
    assertReplace(Lists.newArrayList(OUTSIDE, BEGIN_INGREDIENT, OUTSIDE),
        "xaxa sól xaxa", "sól", unitStub, unitTypeStub);
    assertReplace(Lists.newArrayList(OUTSIDE, OUTSIDE),
        "xaxa xaxa", "sól", unitStub, unitTypeStub);

    assertReplace(
        Lists.newArrayList(BEGIN_QUANTITY, BEGIN_UNIT, BEGIN_INGREDIENT, INSIDE_INGREDIENT),
        "1 kg przecieru pomidorowego", "przecieru pomidorowego", "1", "kg");
    assertReplace(Lists.newArrayList(BEGIN_UNIT, BEGIN_INGREDIENT),
        "kilogram soli", "soli", "", "kilogram");
  }

  private void assertReplace(ArrayList<Labels> expected, String source,
      String ingredientSource, String unitSource, String unitTypeSource) {
    Ingredient ingredient =
        new Ingredient(new PredictResult<>(ingredientSource), getUnit(unitSource, unitTypeSource));
    List<Labels> replaceActual = labelReplacer.replace(source, ingredient);
    Assert.assertEquals(expected, replaceActual);
  }

  private Unit getUnit(String unit, String unitType) {
    return new Unit(new PredictResult<>(unit), new PredictResult<>(unitType));
  }
}
