package com.example.template.features.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.template.databinding.ItemNewsBinding
import com.example.template.model.NewsArticle

class NewsAdapter() :
    ListAdapter<NewsArticle, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = getItem(position)
        (holder as ViewHolder).bind(news, holder)
    }

    class ViewHolder(private val binding: ItemNewsBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsArticle, holder: ViewHolder) {
            binding.apply {
                Glide.with(holder.itemView.context)
                    .load(news.image_url)
                    .thumbnail( 0.5f )
                    .override( 200, 200 )
                    .placeholder(news.placeholderColor.blue)
                    .diskCacheStrategy( DiskCacheStrategy.ALL )
                    .into(newsImage)

                newsTitle.text = news.title
                newsDescription.text = news.description
                newsRating.text = "Rating + ${news.rating} "

            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<NewsArticle>() {
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem?.title == newItem.title
        }
    }



}