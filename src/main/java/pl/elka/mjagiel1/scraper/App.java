package pl.elka.mjagiel1.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.elka.mjagiel1.scraper.storage.dao.RecipeDao;
import pl.elka.mjagiel1.scraper.storage.entities.RecipeEntity;
import pl.elka.mjagiel1.scraper.storage.models.Recipe;

import java.util.List;
import java.util.Optional;

@Component
public class App implements CommandLineRunner {

  @Autowired
  Scraper scraper;

  @Autowired
  private RecipeDao recipeDao;

  @Override
  public void run(String... args) throws Exception {

    Optional<Recipe> recipe = scraper
        .download("https://smaker.pl/przepis-ciasto-chalwowo-czekoladowe,160185,bryssska.html");
    recipe.ifPresent(this::storeResult);

    List<RecipeEntity> recipeCandidates = recipeDao.findRecipeEntitiesByHtmlSourceEquals(null);
    for (int i = 0; i < 10; i++) {
      RecipeEntity recipeEntity = recipeCandidates.get(i);
      Optional<Recipe> recipeCandidate = scraper.download(recipeEntity.getSiteAddress());
      recipeCandidate
          .map(r -> {
            storeResult(r);
            return null;
          })
          .orElseGet(() -> {
            recipeEntity.setHtmlSource("wrong");
            recipeDao.save(recipeEntity);
            return null;
          });
    }
  }

  private void storeResult(Recipe recipe) {
    RecipeEntity recipeEntity = new RecipeEntity();
    recipeEntity.setSiteAddress(recipe.getSiteAddress());
    recipeEntity.setHtmlSource(recipe.getHtmlSource());
    recipeEntity.setIngredients(recipe.getIngredients());
    recipeEntity.setRecipeName(recipe.getRecipeName());
    recipeEntity.setRecipeText(recipe.getRecipeText());
    recipeDao.save(recipeEntity);

    saveRecipeCandidates(recipe);
  }

  private void saveRecipeCandidates(Recipe recipe) {
    for (String href : recipe.getHrefs()) {
      RecipeEntity recipeEntity = recipeDao.findRecipeEntityBySiteAddressEquals(href);
      if (recipeEntity == null) {
        RecipeEntity entity = new RecipeEntity();
        entity.setSiteAddress(href);
        recipeDao.save(entity);
      }
    }
  }
}
