# Spring Boot RDS Cache App

A simple Spring Boot application that connects to an AWS RDS MySQL database, reads table data (e.g., `Article`), and caches it in memory using Spring's `@Cacheable` and `ConcurrentMapCacheManager`.

## 🧱 Features

- Connects to AWS RDS MySQL
- Fetches data using Spring Data JPA
- Caches data in memory at application startup
- Prints cached data using `ApplicationRunner`
- Modular and easy to extend for other tables

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Gradle
- An active AWS RDS MySQL instance
- Database table `article` with sample data

### 🔧 Configuration

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://<RDS-ENDPOINT>:3306/<YOUR_DB>
    username: <USERNAME>
    password: <PASSWORD>
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
  cache:
    type: simple
```

🏗 Build and Run

```
# Build
./gradlew clean build

# Run
./gradlew bootRun
```

On startup, the application:

1. Loads all articles from the database
2. Caches them in memory 
3. Prints the cached articles to the console

📁 Project Structure

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── DemoApplication.java
│   │   ├── model/Article.java
│   │   ├── repository/ArticleRepository.java
│   │   ├── service/ArticleService.java
│   │   ├── service/CacheLoaderService.java
│   │   ├── runner/CachePrinterRunner.java
│   │   └── config/CacheConfig.java
│   └── resources/application.yml
```

📝 License

```angular2html
Let me know if you'd like to add a [Dockerfile](f), [Redis integration](f), or [REST endpoint documentation](f) to the README.
```


