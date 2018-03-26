package pl.elka.mjagiel1.scraper;

import java.util.List;
import java.util.Map;

public class WebRecipe {
  private final String recipeName;
  private final List<String> tags;
  private final Difficulty difficulty;
  private final PreparationTime preparationTime;
  private final Map<String, String> metaValues;
  private final List<String> ingredients;
  private final String recipeText;
  private final RecipeWebMeta webMeta;


  public WebRecipe(String recipeName, List<String> tags,
      Difficulty difficulty, PreparationTime preparationTime,
      Map<String, String> metaValues, List<String> ingredients, String recipeText,
      RecipeWebMeta webMeta) {
    this.recipeName = recipeName;
    this.tags = tags;
    this.difficulty = difficulty;
    this.preparationTime = preparationTime;
    this.metaValues = metaValues;
    this.ingredients = ingredients;
    this.recipeText = recipeText;
    this.webMeta = webMeta;
  }

  @Override public String toString() {
    return "WebRecipe{" +
        "recipeName='" + recipeName + '\'' +
        "\n, tags=" + tags +
        "\n, difficulty=" + difficulty +
        "\n, preparationTime=" + preparationTime +
        "\n, metaValues=" + metaValues +
        "\n, ingredients=" + ingredients +
        "\n, recipeText='" + recipeText + '\'' +
        "\n, webMeta=" + webMeta +
        '}';
  }
}
