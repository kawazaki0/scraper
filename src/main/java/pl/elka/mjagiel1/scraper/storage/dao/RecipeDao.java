package pl.elka.mjagiel1.scraper.storage.dao;

import org.springframework.data.repository.CrudRepository;
import pl.elka.mjagiel1.scraper.storage.entities.RecipeEntity;

import java.util.List;

public interface RecipeDao extends CrudRepository<RecipeEntity, Long> {

  List<RecipeEntity> findRecipeEntitiesByHtmlSourceEquals(String htmlSource);

  RecipeEntity findRecipeEntityBySiteAddressEquals(String siteAddress);

  void deleteRecipeEntityById(Long id);
}
