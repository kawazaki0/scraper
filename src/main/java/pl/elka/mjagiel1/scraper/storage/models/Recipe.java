package pl.elka.mjagiel1.scraper.storage.models;

import pl.elka.mjagiel1.scraper.Difficulty;
import pl.elka.mjagiel1.scraper.PreparationTime;

import java.util.List;
import java.util.Map;

public class Recipe {
  private final String recipeName;
  private final List<String> ingredients;
  private final String recipeText;
  private final List<String> tags;
  private final Difficulty difficulty;
  private final PreparationTime preparationTime;
  private final Map<String, String> metaValues;


  public Recipe(String recipeName, List<String> ingredients, String recipeText,
      List<String> tags, Difficulty difficulty,
      PreparationTime preparationTime, Map<String, String> metaValues) {
    this.recipeName = recipeName;
    this.ingredients = ingredients;
    this.recipeText = recipeText;
    this.tags = tags;
    this.difficulty = difficulty;
    this.preparationTime = preparationTime;
    this.metaValues = metaValues;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public String getRecipeText() {
    return recipeText;
  }

  public List<String> getTags() {
    return tags;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public PreparationTime getPreparationTime() {
    return preparationTime;
  }

  public Map<String, String> getMetaValues() {
    return metaValues;
  }
}
