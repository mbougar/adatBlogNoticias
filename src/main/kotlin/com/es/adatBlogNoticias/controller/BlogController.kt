package com.es.adatBlogNoticias.controller

import com.es.adatBlogNoticias.model.Comment
import com.es.adatBlogNoticias.model.NewsItem
import com.es.adatBlogNoticias.model.User
import com.es.adatBlogNoticias.service.BlogService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class BlogController(private val blogService: BlogService) {

    @PostMapping("/users")
    fun registerUser(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(blogService.registerUser(user))
    }

    @PostMapping("/news")
    fun publishNews(@RequestBody news: NewsItem): ResponseEntity<NewsItem> {
        return ResponseEntity.ok(blogService.publishNews(news))
    }

    @GetMapping("/news/{author}")
    fun listUserNews(@PathVariable author: String): ResponseEntity<List<NewsItem>> {
        return ResponseEntity.ok(blogService.listUserNews(author))
    }

    @GetMapping("/news/tags/{tag}")
    fun searchNewsByTag(@PathVariable tag: String): ResponseEntity<List<NewsItem>> {
        return ResponseEntity.ok(blogService.searchNewsByTag(tag))
    }

    @GetMapping("/news/latest")
    fun listLatestNews(): ResponseEntity<List<NewsItem>> {
        return ResponseEntity.ok(blogService.listLatestNews())
    }

    @PostMapping("/comments")
    fun addComment(@RequestParam newsId: String, @RequestParam username: String, @RequestParam text: String): ResponseEntity<Comment> {
        return ResponseEntity.ok(blogService.addComment(ObjectId(newsId), username, text))
    }

    @GetMapping("/comments/{newsId}")
    fun listNewsComments(@PathVariable newsId: String): ResponseEntity<List<Comment>> {
        return ResponseEntity.ok(blogService.listNewsComments(ObjectId(newsId)))
    }
}