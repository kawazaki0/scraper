package pl.elka.mjagiel1.scraper;

import org.jsoup.nodes.Document;

public interface Extractor {
  WebRecipe extractFromPage(Document page);
}
