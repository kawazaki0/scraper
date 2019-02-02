package pl.elka.mjagiel1.extractor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class IngredientLabeler {
  public IngredientLabel label(String source) {
    IngredientExtractor ingredientExtractor = new IngredientExtractor();
    Ingredient ingredient = ingredientExtractor.extract(source);
//    List<Labels> labels = mapLabel(source, ingredient);
//    return new IngredientLabel(labels);
    return null;
  }

//  private List<Labels> mapLabel(String source, Ingredient ingredient) {
//
//    if (word.equals(ingredient.getUnit().getQuantity())) {
//      return Labels.BEGIN_QUANTITY;
//    }
//    if (word.equals(ingredient.getUnit().getUnitType().getRawType())) {
//      return Labels.BEGIN_UNIT;
//    }
//    if (word.equals(ingredient.getName())) {
//      return Labels.BEGIN_UNIT;
//    }
////    return Labels.OUTSIDE;
//
//    return labels;
//  }

  @AllArgsConstructor
  public enum Labels {
    BEGIN_QUANTITY("B-QUAN"),
    BEGIN_INGREDIENT("B-INGR"),
    INSIDE_INGREDIENT("I-INGR"),
    BEGIN_UNIT("B-UNIT"),
    OUTSIDE("0");

    private final String stringRepresentation;

  }


  @Data
  @AllArgsConstructor
  public class IngredientLabel {
    public List<Labels> labels;
  }
}
