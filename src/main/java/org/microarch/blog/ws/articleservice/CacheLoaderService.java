package org.microarch.blog.ws.articleservice;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheLoaderService {

    private final ArticleService articleService;

    public CacheLoaderService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostConstruct
    public void loadArticlesToCache() {
        articleService.getAllArticles();
        log.info("Articles cached at startup.");
    }
}

@Configuration
class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("articles");
    }
}
