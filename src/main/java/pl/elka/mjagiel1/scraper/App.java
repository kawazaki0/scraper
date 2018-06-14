package pl.elka.mjagiel1.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import pl.elka.mjagiel1.scraper.storage.dao.RecipeDao;
import pl.elka.mjagiel1.scraper.storage.dao.SiteDao;
import pl.elka.mjagiel1.scraper.storage.entities.RecipeEntity;
import pl.elka.mjagiel1.scraper.storage.entities.SiteEntity;
import pl.elka.mjagiel1.scraper.storage.models.Recipe;
import pl.elka.mjagiel1.scraper.storage.models.Site;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class App implements CommandLineRunner {

  Logger log = Logger.getLogger(App.class.getName());

  @Autowired
  Scraper scraper;

  @Autowired
  private RecipeDao recipeDao;

  @Autowired
  private SiteDao siteDao;

  @Override
  public void run(String... args) {

    log.info("start");
    List<SiteEntity> siteCandidates = siteDao.findSiteEntitiesByHtmlSourceEquals(null);
    if (siteCandidates.isEmpty()) {
      Site site = scraper
          .download("https://smaker.pl/przepis-ciasto-chalwowo-czekoladowe,160185,bryssska.html");
      storeResult(site);
    }
    log.info("Init completed");

    for (int j = 0; j < 10; j++) {
      log.info(String.format("iter %d/10", j+1));
      siteCandidates = getSiteCandidates();
      for (int i = 0; i < 100; i++) {

        SiteEntity siteEntity = siteCandidates.get(i);
        Site site = scraper.download(siteEntity.getSiteAddress());
        log.info(String.format("site %s", site.getSiteAddress()));
        storeResult(site);
      }
    }
    log.info("stop");
  }

  private List<SiteEntity> getSiteCandidates() {
    List<SiteEntity> siteCandidates;
    siteCandidates = siteDao.findSiteEntitiesByHtmlSourceEquals(null);
    Stream<SiteEntity> promisingRecipes = siteCandidates.stream()
        .filter(this::isPromisingRecipeAddress);
    Stream<SiteEntity> restRecipes = siteCandidates.stream()
        .filter(recipe -> !isPromisingRecipeAddress(recipe));

    return Stream.concat(promisingRecipes, restRecipes)
        .collect(Collectors.toList());
  }

  private boolean isPromisingRecipeAddress(SiteEntity siteEntity) {
    return siteEntity.getSiteAddress().matches("^https://smaker\\.pl/przepis-.*\\.html$");
  }

  private void storeResult(Site site) {
    List<SiteEntity> siteEntities =
        siteDao.findSiteEntitiesBySiteAddressEquals(site.getSiteAddress());
    SiteEntity siteEntity;
    if (siteEntities.isEmpty()) {
      siteEntity = new SiteEntity();
    } else if (siteEntities.size() == 1) {
      siteEntity = siteEntities.get(0);
    } else {
      throw new RuntimeException("site is not unique: " + site.getSiteAddress());
    }
    siteEntity.setSiteAddress(site.getSiteAddress());
    siteEntity.setHtmlSource(site.getHtmlSource());

    Recipe recipe = site.getRecipe();
    if (recipe != null) {
      RecipeEntity recipeEntity = new RecipeEntity();
      recipeEntity.setIngredients(recipe.getIngredients());
      recipeEntity.setRecipeName(recipe.getRecipeName());
      recipeEntity.setRecipeText(recipe.getRecipeText());
      siteEntity.setRecipe(recipeEntity);
      recipeDao.save(recipeEntity);
      log.info(String.format("recipe %s", recipe.getRecipeName()));
    }
    siteDao.save(siteEntity);

    saveRecipeCandidates(site.getHrefs());
  }

  private void saveRecipeCandidates(List<String> hrefs) {
    if (hrefs != null && !hrefs.isEmpty()) {
      for (String href : hrefs) {
        List<SiteEntity> siteEntities = siteDao.findSiteEntitiesBySiteAddressEquals(href);
        if (siteEntities.isEmpty()) {
          SiteEntity siteEntity = new SiteEntity();
          siteEntity.setSiteAddress(href);
          siteDao.save(siteEntity);
        }
      }
    } else {
      System.err.println("hrefs are empty");
    }
  }
}
