package com.example.hacker_news_aggregator.story

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface StoryClient {
    @GetExchange("/newstories.json")
    fun getNewStories(): List<Int>
    @GetExchange("/beststories.json")
    fun getBestStories(): List<Int>
    @GetExchange("/item/{id}.json")
    fun getStory(@PathVariable id: Int): StoryDTO
}