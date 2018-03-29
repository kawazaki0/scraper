package pl.elka.mjagiel1.scraper.storage.models;

import java.util.List;

public class Site {
  private final String siteAddress;
  private final String htmlSource;
  private final List<String> hrefs;
  private final Recipe recipe;

  public Site(String siteAddress, String htmlSource, List<String> hrefs,
      Recipe recipe) {
    this.siteAddress = siteAddress;
    this.htmlSource = htmlSource;
    this.hrefs = hrefs;
    this.recipe = recipe;
  }

  public String getSiteAddress() {
    return siteAddress;
  }

  public String getHtmlSource() {
    return htmlSource;
  }

  public List<String> getHrefs() {
    return hrefs;
  }

  public Recipe getRecipe() {
    return recipe;
  }
}
