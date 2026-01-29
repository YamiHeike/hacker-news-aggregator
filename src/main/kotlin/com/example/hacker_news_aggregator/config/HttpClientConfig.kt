package com.example.hacker_news_aggregator.config

import com.example.hacker_news_aggregator.story.StoryClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.registry.ImportHttpServices

@Configuration
@ImportHttpServices(basePackages = ["com.example.hacker_news_aggregator.story"], types = [StoryClient::class])
class HttpClientConfig(@Value("\${story-client.base-url}") private val baseUrl: String) {
    @Bean
    fun storyClient(builder: RestClient.Builder): StoryClient {
        val client = builder
            .baseUrl(baseUrl)
            .build()
        val proxyFactory = HttpServiceProxyFactory.builder()
            .exchangeAdapter(RestClientAdapter.create(client))
            .build()
        return proxyFactory.createClient(StoryClient::class.java)
    }
}