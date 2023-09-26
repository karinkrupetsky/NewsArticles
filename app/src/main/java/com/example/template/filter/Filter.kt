package com.example.template.filter

import com.example.template.model.NewsArticle

interface Filter<T> {
    fun applyFilter(item: T): Boolean
}

class TitleFilter(private val title: String) : Filter<NewsArticle> {
    override fun applyFilter(item: NewsArticle): Boolean {
        return item.title.contains(title, ignoreCase = true)
    }
}

class RatingFilter(private val minRating: Int) : Filter<NewsArticle> {
    override fun applyFilter(item: NewsArticle): Boolean {
        return item.rating >= minRating
    }
}

