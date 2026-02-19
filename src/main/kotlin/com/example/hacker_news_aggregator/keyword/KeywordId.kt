package com.example.hacker_news_aggregator.keyword

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
class KeywordId(val id: UUID) {
}

fun generateKeywordId(): KeywordId {
    return KeywordId(UUID.randomUUID())
}