package com.es.adatBlogNoticias.repository

import com.es.adatBlogNoticias.model.NewsItem
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NewsRepository : MongoRepository<NewsItem, ObjectId> {
    fun findByAuthor(author: String): List<NewsItem>
    fun findByTagsContaining(tag: String): List<NewsItem>
    fun findTop10ByOrderByPublicationDateDesc(): List<NewsItem>
}