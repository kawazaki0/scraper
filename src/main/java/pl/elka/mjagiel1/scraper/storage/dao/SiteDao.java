package pl.elka.mjagiel1.scraper.storage.dao;

import org.springframework.data.repository.CrudRepository;
import pl.elka.mjagiel1.scraper.storage.entities.SiteEntity;

import java.util.List;

public interface SiteDao extends CrudRepository<SiteEntity, Long> {

  List<SiteEntity> findSiteEntitiesByHtmlSourceEquals(String htmlSource);

  List<SiteEntity> findSiteEntitiesByHtmlSourceNotAndHtmlSourceNotNullAndSiteAddressLike(String notHtmlSource, String addressLike);

  List<SiteEntity> findSiteEntitiesBySiteAddressEquals(String siteAddress);

  default List<SiteEntity> findNotEmptyRecipes() {
    return findSiteEntitiesByHtmlSourceNotAndHtmlSourceNotNullAndSiteAddressLike("error", "https://smaker.pl/przepis-%.html");
  }

}
