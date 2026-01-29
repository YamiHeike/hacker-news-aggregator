package com.example.hacker_news_aggregator.story

data class StoryDTO(
    val by: String,
    val descendants: Int,
    val id: Long,
    val kids: List<Long> = emptyList(),
    val score: Int,
    val time: Long,
    val title: String,
    val type: String,
    val url: String?,
) {
    val link: String
        get() = "https://news.ycombinator.com/item?id=$id"
}