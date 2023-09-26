package com.example.template.features.news

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.template.R
import com.example.template.databinding.ItemNewsBinding
import com.example.template.model.NewsArticle
import com.example.template.model.PlaceHolderColor

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
                    .override( 100, 100 )
                    .placeholder(createColorDrawable(news.placeholderColor, holder.itemView.context))
                    .diskCacheStrategy( DiskCacheStrategy.ALL )
                    .into(newsImage)

                newsTitle.text = news.title
                newsDescription.text = news.description
                newsRating.text = "Rating + ${news.rating} "

            }
        }

        private fun createColorDrawable(placeHolderColor: PlaceHolderColor, context: Context): Drawable {
            // Create a bitmap with the specified color
            val color = Color.rgb(placeHolderColor.red, placeHolderColor.green, placeHolderColor.blue)
            val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawColor(color)

            // Convert the bitmap to a drawable
            return BitmapDrawable(context.resources, bitmap)
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