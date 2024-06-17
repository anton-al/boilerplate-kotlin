package net.sharpdev.boilerplate

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

    @GetMapping("/")
    @ApiResponses(
        value = arrayOf(
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "401", description = "You are not authorized access the resource"),
            ApiResponse(responseCode = "404", description = "The resource not found")
        )
    )
    fun findAll() = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    @ApiResponses(
        value = arrayOf(
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "401", description = "You are not authorized access the resource"),
            ApiResponse(responseCode = "404", description = "The resource not found")
        )
    )
    fun findOne(@PathVariable slug: String) =
        repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

    @PostMapping("/")
    @Operation(summary = "Create a new article")
    fun create(@RequestBody article: Article) = repository.save(article)

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing article")
    fun update(@PathVariable id: Long, @RequestBody updatedArticle: Article): Article {
        return repository.findById(id).map {
            it.title = updatedArticle.title
            it.headline = updatedArticle.headline
            it.content = updatedArticle.content
            it.author = updatedArticle.author
            it.slug = updatedArticle.slug
            repository.save(it)
        }.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist") }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an article by ID")
    fun delete(@PathVariable id: Long) {
        return repository.findById(id).map {
            repository.delete(it)
        }.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist") }
    }
}

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "Operations related to Users")
class UserController(private val repository: UserRepository) {

    @GetMapping("/")
    @Operation(summary = "Get all users")
    fun findAll() = repository.findAll()

    @GetMapping("/{login}")
    @Operation(summary = "Get user by login")
    fun findOne(@PathVariable login: String) =
        repository.findByLogin(login) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")

    @PostMapping("/")
    @Operation(summary = "Create a new user")
    fun create(@RequestBody user: User) = repository.save(user)

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    fun update(@PathVariable id: Long, @RequestBody updatedUser: User): User {
        return repository.findById(id).map {
            it.login = updatedUser.login
            it.firstname = updatedUser.firstname
            it.lastname = updatedUser.lastname
            it.description = updatedUser.description
            repository.save(it)
        }.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist") }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    fun delete(@PathVariable id: Long) {
        return repository.findById(id).map {
            repository.delete(it)
        }.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist") }
    }
}