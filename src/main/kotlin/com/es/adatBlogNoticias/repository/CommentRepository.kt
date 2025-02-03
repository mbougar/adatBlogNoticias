package com.es.adatBlogNoticias.repository

import com.es.adatBlogNoticias.model.Comment
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<Comment, ObjectId> {
    fun findByNewsId(newsId: ObjectId): List<Comment>
}