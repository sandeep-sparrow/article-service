package org.microarch.blog.ws.articleservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class ArticleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleServiceApplication.class, args);
    }

}

@Entity
@Data
class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author;
    private LocalDateTime published_at;
}

interface ArticleRepository extends JpaRepository<Articles, Long> {
}

@Service
class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Cacheable("articles")
    public List<Articles> getAllArticles() {
        return articleRepository.findAll();
    }
}

@Component
@Slf4j
class CachePrinterRunner implements ApplicationRunner {

    private final ArticleService articleService;

    public CachePrinterRunner(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Articles> cachedArticles = articleService.getAllArticles();
        log.info("=== Cached Articles ===");
        cachedArticles.forEach(article ->
                log.info(article.getId() + ": " + article.getTitle())
        );
    }
}
