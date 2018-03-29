package pl.elka.mjagiel1.scraper.storage.dao;

import org.springframework.data.repository.CrudRepository;
import pl.elka.mjagiel1.scraper.storage.entities.RecipeEntity;

public interface RecipeDao extends CrudRepository<RecipeEntity, Long> {


}
