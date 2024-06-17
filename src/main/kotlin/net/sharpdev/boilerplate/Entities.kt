package net.sharpdev.boilerplate

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import toSlug
import java.time.LocalDateTime

@Entity
@Schema(description = "User entity representing an article in the system")
class Article(
    @Schema(description = "Title of the article")
    var title: String,
    @Schema(description = "Headline of the article")
    var headline: String,
    @Schema(description = "Content of the article")
    var content: String,
    @Schema(description = "Author of the article")
    @ManyToOne var author: User,
    @Schema(description = "slug of the article")
    var slug: String = title.toSlug(),
    @Schema(description = "Added time of the article")
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Schema(description = "Unique identifier of the article")
    @Id @GeneratedValue var id: Long? = null)

@Entity
@Table(name = "\"user\"")
@Schema(description = "User entity representing a user in the system")
class User(
    @Schema(description = "Login name of the user")
    var login: String,
    @Schema(description = "First name of the user")
    var firstname: String,
    @Schema(description = "Last name of the user")
    var lastname: String,
    @Schema(description = "Description of the user")
    var description: String? = null,
    @Schema(description = "Unique identifier of the user")
    @Id @GeneratedValue var id: Long? = null)