package com.example.template.features

import com.example.template.repositories.NewsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.filter.Filter
import com.example.template.model.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {

    init {
        getNewsArticles()
    }

    private var originalList: List<NewsArticle> = listOf()
    private val _newsArticles = MutableLiveData<List<NewsArticle>>()
    val newsArticles: LiveData<List<NewsArticle>> get() = _newsArticles


    private fun getNewsArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val articles = repository.getNewsArticles()
            originalList = articles
            _newsArticles.postValue(articles)
        }
    }

    fun filterNewsArticles(filters: List<Filter<NewsArticle>>) {
        val filteredList = originalList.filter { article ->
            filters.all { filter -> filter.applyFilter(article) }
        }
        _newsArticles.postValue(filteredList)
    }
}
