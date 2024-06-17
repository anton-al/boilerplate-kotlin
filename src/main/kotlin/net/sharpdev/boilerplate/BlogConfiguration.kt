package net.sharpdev.boilerplate

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("base-service")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun customOpenAPI(
        @Value("\${application-description}") appDescription: String?, @Value(
            "\${application-version}"
        ) appVersion: String?
    ): OpenAPI {
        val contact = Contact()
        contact.email = "anton.alputov@sharp-dev.net"
        contact.name = "Anton"
        contact.url = "https://t.me/alputov"
        return OpenAPI()
            .info(
                Info()
                    .title("Microservice Base Service Application API")
                    .version(appVersion)
                    .description(appDescription)
                    .termsOfService("http://swagger.io/terms/")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
                    .contact(contact)
            )
    }

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val johnDoe = userRepository.save(User("johnDoe", "John", "Doe"))
        articleRepository.save(Article(
            title = "Lorem",
            headline = "Lorem",
            content = "dolor sit amet",
            author = johnDoe
        ))
        articleRepository.save(Article(
            title = "Ipsum",
            headline = "Ipsum",
            content = "dolor sit amet",
            author = johnDoe
        ))
    }
}