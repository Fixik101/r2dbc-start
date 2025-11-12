package com.example.habrr2dbcstart.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    val id: Long? = null,
    val username: String? = null,
    val email: String? = null
)