package com.example.hacker_news_aggregator.story

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/story")
class StoryController {
    private val storyClient: StoryClient

    constructor(storyClient: StoryClient) {
        this.storyClient = storyClient
    }

    @GetMapping("/new")
    fun getNewStories(): ResponseEntity<List<StoryDTO>> {
        val storyIds = storyClient.getNewStories().take(50)
        val stories = storyIds.mapNotNull { storyClient.getStory(it) }

        return ResponseEntity.ok(stories)
    }

    @GetMapping("/top")
    fun getTopStories(): ResponseEntity<List<StoryDTO>> {
        val storyIds = storyClient.getBestStories().take(50)
        val stories = storyIds.mapNotNull { storyClient.getStory(it) }

        return ResponseEntity.ok(stories)
    }

    @GetMapping("/{storyId}")
    fun getStory(@PathVariable storyId: Long): ResponseEntity<StoryDTO> {
        val story = storyClient.getStory(storyId)
        return story?.let { ResponseEntity.ok(it) }?: ResponseEntity.notFound().build()
    }
}