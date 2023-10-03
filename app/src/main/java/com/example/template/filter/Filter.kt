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
//        return item.rating == minRating
//    }
//}


class StringFilter<T>(private val articleParam: (T) -> String?,
                      private val stringValue: String) : Filter<T> {
    override fun applyFilter(item: T): Boolean {
        val filterValue = articleParam(item)
        return  filterValue != null && filterValue.contains(stringValue, ignoreCase = true)
    }
}

fun <T>applyStringFilter(filters: MutableList<Filter<T>>, articleParam: (T) -> String?, value: String, filterBy: String = ""): String {
    if(value.isNotEmpty()) {
        filters.add(StringFilter(articleParam, value))
        return " filtering by : ${filterBy} : ${value}"
    }
    return ""
}

class IntFilter<T>(private val articleParam: (T) -> Int?,
                   private val intValue: Int) : Filter<T> {
    override fun applyFilter(item: T): Boolean {
        val filterValue = articleParam(item)
        return filterValue != null && filterValue >= intValue
    }
}

fun <T>applyIntFilter(filters: MutableList<Filter<T>>, articleParam: (T) -> Int?, value: Int, filterBy: String = ""): String  {
    filters.add(IntFilter(articleParam, value))
    return  " filtering by : ${filterBy} : ${value}"
}
