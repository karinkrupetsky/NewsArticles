package com.example.template.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.ActivityMainBinding
import com.example.template.features.news.NewsAdapter
import com.example.template.filter.Filter
import com.example.template.filter.RatingFilter
import com.example.template.filter.TitleFilter
import com.example.template.model.NewsArticle
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        initObservers()
    }

    private fun initObservers() {
        viewModel.newsArticles.observe(this) { list ->
            list?.let {
                newsAdapter?.submitList(it)
            }

        }
    }

    private fun initUi() {
        binding.apply {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.layoutManager = layoutManager
            newsAdapter = NewsAdapter()
            recyclerView.adapter = newsAdapter

            filterButton.setOnClickListener {
                val titleFilter = titleFilterEditText.text.toString()
                val ratingFilter = ratingFilterSeekBar.progress

                val filters = mutableListOf<Filter<NewsArticle>>()
                if (titleFilter.isNotEmpty()) {
                    filters.add(TitleFilter(titleFilter))
                }

                filters.add(RatingFilter(ratingFilter))
                viewModel.filterNewsArticles(filters)

            }
        }
    }
}

