package pl.elka.mjagiel1.extractor;

import lombok.AllArgsConstructor;

import java.util.List;

public class IngredientLabeler {

  private LabelReplacer labelReplacer = new LabelReplacer();

  public List<Labels> label(String source) {
    IngredientExtractor ingredientExtractor = new IngredientExtractor();
    Ingredient ingredient = ingredientExtractor.extract(source);
    List<Labels> labels = labelReplacer.replace(source, ingredient);
    return labels;
  }

  @AllArgsConstructor
  public enum Labels {
    BEGIN_QUANTITY("B-QUAN"),
    INSIDE_QUANTITY("I-QUAN"),
    BEGIN_INGREDIENT("B-INGR"),
    INSIDE_INGREDIENT("I-INGR"),
    BEGIN_UNIT("B-UNIT"),
    INSIDE_UNIT("I-UNIT"),
    OUTSIDE("0");

    private final String stringRepresentation;

  }
}
