package pl.elka.mjagiel1.scraper.storage.entities;

import pl.elka.mjagiel1.scraper.storage.models.Recipe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Site")
public class SiteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String siteAddress;

  @Column(unique=true)
  private String htmlSource;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipe_id")
  private RecipeEntity recipe;

  public Long getId() {
    return id;
  }

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

  public RecipeEntity getRecipe() {
    return recipe;
  }

  public void setRecipe(RecipeEntity recipe) {
    this.recipe = recipe;
  }
}
