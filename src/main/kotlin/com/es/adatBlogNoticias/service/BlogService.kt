package com.es.adatBlogNoticias.service

import com.es.adatBlogNoticias.model.Comment
import com.es.adatBlogNoticias.model.NewsItem
import com.es.adatBlogNoticias.model.User
import com.es.adatBlogNoticias.repository.CommentRepository
import com.es.adatBlogNoticias.repository.NewsRepository
import com.es.adatBlogNoticias.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.Date

@Service
class BlogService(
    private val userRepository: UserRepository,
    private val newsRepository: NewsRepository,
    private val commentRepository: CommentRepository
) {

    fun registerUser(user: User): User {
        if (userRepository.existsById(user.id) || userRepository.findByUsername(user.username) != null) {
            throw IllegalArgumentException("Email o username ya en uso")
        }
        return userRepository.save(user)
    }

    fun publishNews(news: NewsItem): NewsItem {
        if (!userRepository.existsById(news.author)) {
            throw IllegalArgumentException("Usuario no registrado")
        }
        return newsRepository.save(news)
    }

    fun listUserNews(author: String): List<NewsItem> = newsRepository.findByAuthor(author)

    fun listNewsComments(newsId: ObjectId): List<Comment> = commentRepository.findByNewsId(newsId)

    fun searchNewsByTag(tag: String): List<NewsItem> = newsRepository.findByTagsContaining(tag)

    fun listLatestNews(): List<NewsItem> = newsRepository.findTop10ByOrderByPublicationDateDesc()

    fun addComment(newsId: ObjectId, username: String, text: String): Comment {
        val user = userRepository.findByUsername(username) ?: throw IllegalArgumentException("Usuario no encontrado")
        if (user.banStatus || !user.activeStatus) {
            throw IllegalArgumentException("Usuario no autorizado para comentar")
        }
        if (!newsRepository.existsById(newsId)) {
            throw IllegalArgumentException("Noticia no encontrada")
        }
        val comment = Comment(newsId = newsId, username = username, body = text, timestamp = Date())
        return commentRepository.save(comment)
    }
}