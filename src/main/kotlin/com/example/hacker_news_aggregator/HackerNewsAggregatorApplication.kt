package com.example.hacker_news_aggregator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HackerNewsAggregatorApplication

fun main(args: Array<String>) {
	runApplication<HackerNewsAggregatorApplication>(*args)
}
