package com.example.template.features

import com.example.template.repositories.NewsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.model.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {

    init {
        getNewsArticles()
    }

    // LiveData to hold the list of news articles
    private val _newsArticles = MutableLiveData<List<NewsArticle>>()
    val newsArticles: LiveData<List<NewsArticle>> get() = _newsArticles

    // Function to fetch news articles from the repository
    private fun getNewsArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val articles = repository.getNewsArticles()
            _newsArticles.postValue(articles)
        }
    }

    // Function to filter news articles by title and rating
    fun filterNewsArticles(title: String, rating: Int) {
        viewModelScope.launch {
            val filteredArticles = repository.filterNewsArticlesByTitleAndRating(title, rating)
            _newsArticles.postValue(filteredArticles)
        }
    }
}
