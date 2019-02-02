package pl.elka.mjagiel1;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.elka.mjagiel1.extractor.tagger.MorfologicStemmer;
import pl.elka.mjagiel1.extractor.tagger.Tagset;
import pl.elka.mjagiel1.scraper.storage.dao.SiteDao;
import pl.elka.mjagiel1.scraper.storage.entities.SiteEntity;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class PrepareDataToWord2Vec implements CommandLineRunner {

  private MorfologicStemmer morfologicStemmer = new MorfologicStemmer();

  @Autowired
  private SiteDao siteDao;

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(PrepareDataToWord2Vec.class, args);
    applicationContext.close();
  }

  @Override public void run(String... strings) throws Exception {
    List<SiteEntity> sources = siteDao.findNotEmptyRecipes();
    List<String> collect = sources.stream()
        .map(SiteEntity::getHtmlSource)
        .map(Jsoup::parse)
        .map(html -> html.body().text())
        .map(txt -> morfologicStemmer.stem(txt))
        .map(tagsets -> tagsets.stream()
            .map(Tagset::getLemma)
            .collect(Collectors.joining(" ")))
        .map(String::toLowerCase)
        .map(input -> input.replaceAll("\\p{Punct}", " "))
        .collect(Collectors.toList());
    System.out.println(collect);
    Files.write(Paths.get("words_from_sites.txt"), collect.stream().collect(Collectors.joining("\n")).getBytes());

  }
}
