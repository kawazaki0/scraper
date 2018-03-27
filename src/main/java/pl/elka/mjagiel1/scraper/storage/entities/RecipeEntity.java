package pl.elka.mjagiel1.scraper.storage.entities;

import org.jsoup.nodes.Document;
import pl.elka.mjagiel1.scraper.Difficulty;
import pl.elka.mjagiel1.scraper.PreparationTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

  private String siteAddress;
  private String htmlSource;

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public List<String> getIngredients() {
    return Arrays.asList(this.ingredients.split("\\s*;\\s*"));
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients.stream()
        .collect(Collectors.joining(";"));
  }

  public String getRecipeText() {
    return recipeText;
  }

  public void setRecipeText(String recipeText) {
    this.recipeText = recipeText;
  }

//  public String getMetaValues() {
//    return metaValues;
//  }
//
//  public void setMetaValues(String metaValues) {
//    this.metaValues = metaValues;
//  }

  public String getSiteAddress() {
    return siteAddress;
  }

  public void setSiteAddress(String siteAddress) {
    this.siteAddress = siteAddress;
  }

  public String getHtmlSource() {
    return htmlSource;
  }

  public void setHtmlSource(String htmlSource) {
    this.htmlSource = htmlSource;
  }

  public Long getId() {
    return id;
  }

  //  public List<String> getHrefs() {
//    return hrefs;
//  }
//
//  public void setHrefs(List<String> hrefs) {
//    this.hrefs = hrefs;
//  }
}
