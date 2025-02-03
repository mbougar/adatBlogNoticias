package com.es.adatBlogNoticias.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    @Id val id: String, // Email como identificador único
    val fullname: String,
    val username: String,
    val banStatus: Boolean,
    val activeStatus: Boolean,
    val address: Address,
    val phones: List<String>
)