package com.example.template.features

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.ActivityMainBinding
import com.example.template.features.news.NewsAdapter
import com.example.template.filter.Filter


import com.example.template.model.NewsArticle
import org.koin.androidx.viewmodel.ext.android.viewModel



import com.example.template.filter.applyIntFilter
import com.example.template.filter.applyStringFilter



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
                val description = descriptionEditText.text.toString()

                val filters = mutableListOf<Filter<NewsArticle>>()
                var logInfo = ""

//                if (titleFilter.isNotEmpty()) {
//                    logInfo = "filtering by title : ${titleFilter}"
//                    filters.add(TitleFilter(titleFilter))
//                }
//                if(ratingFilter>0){
//                    filters.add(RatingFilter(ratingFilter))}


                logInfo += applyStringFilter(filters, {it.title}, titleFilter, "title")
                logInfo += applyStringFilter(filters, {it.description}, description, "description")


                if(ratingFilter > 0) {
                    logInfo += applyIntFilter(filters, {it.rating}, ratingFilter, "rating")
                }

                if(logInfo.isNotEmpty()) {
                    Toast.makeText(this@MainActivity, "${logInfo}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Showing Original list", Toast.LENGTH_LONG).show()
                }

                viewModel.filterNewsArticles(filters)

            }
        }
    }



}