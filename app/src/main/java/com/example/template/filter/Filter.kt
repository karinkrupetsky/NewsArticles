package com.example.template.filter

import android.util.Log
import com.example.template.model.NewsArticle

interface Filter<T> {
    fun applyFilter(item: T): Boolean
}


//class TitleFilter(private val title: String) : Filter<NewsArticle> {
//    override fun applyFilter(item: NewsArticle): Boolean {
//        return item.title.contains(title, ignoreCase = true)
//    }
//}
//
//class RatingFilter(private val minRating: Int) : Filter<NewsArticle> {
//    override fun applyFilter(item: NewsArticle): Boolean {
//        return item.rating >= minRating
//    }
//}

class StringFilter(private val articleParam: (NewsArticle) -> String?,
                   private val stringValue: String) : Filter<NewsArticle> {
    override fun applyFilter(item: NewsArticle): Boolean {
        val filterValue = articleParam(item)
        return  filterValue != null && filterValue.contains(stringValue, ignoreCase = true)
    }
}

class IntFilter(private val articleParam: (NewsArticle) -> Int?,
                private val intValue: Int) : Filter<NewsArticle> {
    override fun applyFilter(item: NewsArticle): Boolean {
        val filterValue = articleParam(item)
        return filterValue != null && filterValue == intValue
    }
}

