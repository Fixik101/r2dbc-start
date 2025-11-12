package com.example.habrr2dbcstart

import com.example.habrr2dbcstart.entity.User
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

class UserRepositoryTest : BaseRepositoryTest() {

    @Test
    fun `findByUsername - success`() {
        val username = "testUser"
        val email = "test@gmail.com"
        StepVerifier.create(userRepository.save(
            User(null, username, email)
        )).expectNextMatches { it.id != null }
            .verifyComplete()

        val result = userRepository.findByUsername(username)
        StepVerifier.create(result)
            .expectNextMatches {
                it.username == username
                it.email == email
            }
            .verifyComplete()
    }

    @Test
    fun `findByUsername - not found`() {
        val result = userRepository.findByUsername("qwerty")
        StepVerifier.create(result)
            .verifyComplete()
    }

    @Test
    fun `findAllByUsernames - success`() {
        val username1 = "testUser1"
        val email1 = "test1@gmail.com"
        StepVerifier.create(userRepository.save(
            User(null, username1, email1)
        )).expectNextMatches { it.id != null }
            .verifyComplete()
        val username2 = "testUser2"
        val email2 = "test2@gmail.com"
        StepVerifier.create(userRepository.save(
            User(null, username2, email2)
        )).expectNextMatches { it.id != null }
            .verifyComplete()

        val result = userRepository.findAllByUsernames(listOf("testUser1", "testUser2"))
        StepVerifier.create(result)
            .expectNextMatches {
                it.username == username1
                it.email == email1
            }
            .expectNextMatches {
                it.username == username2
                it.email == email2
            }
            .verifyComplete()
    }
}