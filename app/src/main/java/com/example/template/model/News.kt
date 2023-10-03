package com.example.template.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Articles(
    val articles: List<NewsArticle>
): Serializable, Parcelable

@Parcelize
data class NewsArticle(
    val title: String,
    val description: String,
    val image_url: String,
    val rating: Int,
    val placeholderColor: PlaceHolderColor?
): Parcelable
@Parcelize
data class PlaceHolderColor(
    val red: Int = 195,
    val green: Int = 186,
    val blue:Int = 177
): Parcelable

