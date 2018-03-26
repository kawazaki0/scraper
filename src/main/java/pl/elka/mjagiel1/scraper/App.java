package pl.elka.mjagiel1.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class App implements CommandLineRunner {

  @Autowired
  Scraper scraper;

  @Override
  public void run(String... args) throws Exception {

    WebRecipe recipe = scraper.download(
        "https://smaker.pl/przepis-ciasto-chalwowo-czekoladowe,160185,bryssska.html");

    System.out.println(recipe.toString());
    System.out.println("xaxa");
  }
}
