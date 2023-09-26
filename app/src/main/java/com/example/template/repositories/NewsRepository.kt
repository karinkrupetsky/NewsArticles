package com.example.template.repositories

import LocalDataSource
import com.example.template.model.NewsArticle


class NewsRepository(private val localDataSource: LocalDataSource) {

    // Function to get a list of news articles
    suspend fun getNewsArticles(): List<NewsArticle> {
        return localDataSource.getNewsArticles()
    }
}
