import android.content.Context
import android.util.Log
import com.example.template.model.Articles
import com.example.template.model.NewsArticle
import com.example.template.model.PlaceHolderColor
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class LocalDataSource(private val context: Context) {

    private val jsonFileName = "articles.json"

    suspend fun getNewsArticles(): List<NewsArticle> {
        val jsonString = loadJsonFromAsset(jsonFileName)
        return parseJsonToNewsArticles(jsonString)
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
        try {
            val gson = Gson()
            val articlesObject: Articles = gson.fromJson(jsonString, Articles::class.java)
            return articlesObject.articles
        } catch (e: JSONException) {
            e.printStackTrace()
            return listOf()
        }
    }


}



