package com.example.habrr2dbcstart.repository

import com.example.habrr2dbcstart.entity.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, Long> {

    fun findByUsername(username: String): Mono<User>

    fun findByEmailContaining(emailPart: String): Mono<User>

    @Query("select * from users where username in (:usernames)")
    fun findAllByUsernames(usernames: List<String>): Flux<User>
}