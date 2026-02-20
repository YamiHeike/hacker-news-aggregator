package com.example.hacker_news_aggregator.common

import com.example.hacker_news_aggregator.HackerNewsAggregatorApplication
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@ActiveProfiles("test")
@SpringBootTest
abstract class BaseIntegrationTest {
    @TestConfiguration(proxyBeanMethods = false)
    class TestConfig {
        @Bean
        @ServiceConnection
        fun postgresContainer(): PostgreSQLContainer<*> {
            return PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
        }
    }
}