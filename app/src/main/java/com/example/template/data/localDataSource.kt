import android.content.Context
import com.example.template.model.NewsArticle
import com.example.template.model.PlaceHolderColor
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class LocalDataSource(private val context: Context) {

    private val jsonFileName = "articles.json"

    suspend fun getNewsArticles(): List<NewsArticle> {
        val jsonString = loadJsonFromAsset(jsonFileName)
        return parseJsonToNewsArticles(jsonString)
    }

    suspend fun filterNewsArticlesByTitleAndRating(title: String, rating: Int): List<NewsArticle> {
        val allArticles = getNewsArticles()

        return allArticles.filter {
            it.title.contains(title, ignoreCase = true) && it.rating >= rating
        }
    }


    private fun loadJsonFromAsset(filename: String): String? {
        var json: String? = null
        try {
            val inputStream = context.assets.open("articles.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }


    private fun parseJsonToNewsArticles(jsonString: String?): List<NewsArticle> {
        val articles = mutableListOf<NewsArticle>()

        jsonString?.let {
            try {
                val jsonArray = JSONArray(jsonString)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val title = jsonObject.getString("title")
                    val description = jsonObject.getString("description")
                    val imageURL = jsonObject.getString("image_url")
                    val rating = jsonObject.getInt("rating")
                    val placeholderColor = PlaceHolderColor(
                        jsonObject.getJSONObject("placeholderColor").getInt("red"),
                        jsonObject.getJSONObject("placeholderColor").getInt("green"),
                        jsonObject.getJSONObject("placeholderColor").getInt("blue")
                    )

                    val article =
                        NewsArticle(title, description, imageURL, rating, placeholderColor)
                    articles.add(article)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        return articles
    }
}



