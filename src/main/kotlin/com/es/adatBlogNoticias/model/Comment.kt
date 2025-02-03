package com.es.adatBlogNoticias.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "comments")
data class Comment(
    @Id val id: ObjectId = ObjectId(),
    val username: String,
    val newsId: ObjectId,
    val body: String,
    val timestamp: Date = Date()
)