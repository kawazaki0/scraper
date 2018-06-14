package pl.elka.mjagiel1.scraper.storage.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Recipe")
public class RecipeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String recipeName;
  private String ingredients;
  private String recipeText;
  //  private List<String> tags;
  //  private Difficulty difficulty;
  //  private PreparationTime preparationTime;
  //  private String metaValues;

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public List<String> getIngredients() {
    return Arrays.stream(this.ingredients.split("\\s*;\\s*"))
        .map(i -> i.replace("\\;", ";"))
        .collect(Collectors.toList());
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients.stream()
        .map(i -> i.replace(";", "\\;"))
        .collect(Collectors.joining(";"));
  }

  public String getRecipeText() {
    return recipeText;
  }

  public void setRecipeText(String recipeText) {
    this.recipeText = recipeText;
  }

  public Long getId() {
    return id;
  }

}
