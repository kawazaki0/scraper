package pl.elka.mjagiel1.scraper;

import org.jsoup.nodes.Document;

import java.util.List;

public class RecipeWebMeta {
  private final Document htmlSource;
  private final String siteAddress;
  private final List<String> hrefs;

  public RecipeWebMeta(Document htmlSource, String siteAddress, List<String> hrefs) {
    this.htmlSource = htmlSource;
    this.siteAddress = siteAddress;
    this.hrefs = hrefs;
  }

  @Override public String toString() {
    return "RecipeWebMeta{" +
        "siteAddress=" + siteAddress +
        ", hrefs size=" + hrefs.size() +
        '}';
  }
}
