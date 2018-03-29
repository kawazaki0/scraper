package pl.elka.mjagiel1.scraper.storage.dao;

import org.springframework.data.repository.CrudRepository;
import pl.elka.mjagiel1.scraper.storage.entities.SiteEntity;

import java.util.List;

public interface SiteDao extends CrudRepository<SiteEntity, Long> {

  List<SiteEntity> findSiteEntitiesByHtmlSourceEquals(String htmlSource);

  List<SiteEntity> findSiteEntitiesBySiteAddressEquals(String siteAddress);

}
