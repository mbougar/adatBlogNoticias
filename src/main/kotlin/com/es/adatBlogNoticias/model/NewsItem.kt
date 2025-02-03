package com.es.adatBlogNoticias.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "news")
data class NewsItem(
    @Id val id: ObjectId = ObjectId(),
    val title: String,
    val body: String,
    val publicationDate: Date,
    val author: String,
    val tags: List<String> = emptyList()
)