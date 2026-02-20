package com.example.hacker_news_aggregator.keyword

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Keyword(
    @Id
    @AttributeOverride(name = "id", column = Column(name = "keyword_id"))
    val id: KeywordId?,
    val word: String) {
}