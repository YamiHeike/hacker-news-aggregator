package com.example.hacker_news_aggregator.story

import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit5.WireMockExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource

object WireMockSupport {
    val extension: WireMockExtension by lazy { WireMockExtension.newInstance()
        .options(
            WireMockConfiguration.options()
                .dynamicPort()
                .usingFilesUnderClasspath("wiremock/story")
        ).build()
    }
}

@SpringBootTest
@ActiveProfiles("test")
class StoryClientTest {
    @Autowired
    lateinit var storyClient: StoryClient

    companion object {
        @RegisterExtension
        val wireMock = WireMockSupport.extension;

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("story-client.base-url") { wireMock.baseUrl() }
        }
    }

    @Test
    fun returnCorrectStoryBestStoryList() {
        // given
        val expectedStories = listOf(46564696L, 46565132L)
        // when
        val actualStories = storyClient.getBestStories()
        // then
        assertThat(actualStories).isEqualTo(expectedStories)
    }

    @Test
    fun returnCorrectNewStoryList() {
        // given
        val expectedStories = listOf(46817564L,46817511L)
        // when
        val actualStories = storyClient.getNewStories()
        // then
        assertThat(actualStories).isEqualTo(expectedStories)
    }

    @Test
    fun returnsCorrectItem() {
        // given
        val expectedItem = StoryDTO(
            by = "skadamat",
            descendants = 185,
            id = 46565132,
            kids = listOf(46565679, 46565375, 46565462),
            score = 467,
            time = 1768047800,
            title= "Eulogy for Dark Sky, a data visualization masterpiece (2023)",
            type = "story",
            url = "https://nightingaledvs.com/dark-sky-weather-data-viz/"
        )
        // when
        val actualItem = storyClient.getStory(46565132)
        // then
        assertThat(expectedItem).isEqualTo(actualItem)
    }
}