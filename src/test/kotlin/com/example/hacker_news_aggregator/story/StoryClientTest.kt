package com.example.hacker_news_aggregator.story

import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit5.WireMockExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource

class StoryClientTest {
    companion object {
        @JvmField
        @RegisterExtension
        val wireMock: WireMockExtension = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig()
                .dynamicPort()
                .usingFilesUnderClasspath("wiremock/story"))
            .build()

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("story-client.base-url") { wireMock.baseUrl() }
        }
    }

    @Test
    fun returnCorrectStoryList() {

    }
}