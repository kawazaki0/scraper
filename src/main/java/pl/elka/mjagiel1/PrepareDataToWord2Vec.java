package pl.elka.mjagiel1;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.elka.mjagiel1.extractor.tagger.MorfologicStemmer;
import pl.elka.mjagiel1.scraper.storage.dao.RecipeDao;
import pl.elka.mjagiel1.scraper.storage.dao.SiteDao;
import pl.elka.mjagiel1.scraper.storage.entities.SiteEntity;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class PrepareDataToWord2Vec {

  private static MorfologicStemmer morfologicStemmer = new MorfologicStemmer();

  @Autowired
  private static RecipeDao recipeDao;

  @Autowired
  private static SiteDao siteDao;

  public static void main(String[] args) {
    List<SiteEntity> sources = siteDao.findSiteEntitiesByHtmlSourceNotNull();
    List<String> strings = sources.stream()
        .map(SiteEntity::getHtmlSource)
        .map(Jsoup::parse)
        .map(html -> html.body().text())
        .collect(Collectors.toList());
    System.out.println(strings);
    //    ConfigurableApplicationContext applicationContext = SpringApplication.run(PrepareDataToWord2Vec.class, args);
    //    applicationContext.close();
  }
}
