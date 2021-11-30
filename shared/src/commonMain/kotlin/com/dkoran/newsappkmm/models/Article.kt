package com.dkoran.newsappkmm.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("author")
    val author: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String? = null,
    @SerialName("source")
    var source: Source? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("urlToImage")
    val urlToImage: String? = null
)
fun Article.toRealmArticle(): ArticleRealmDB {
    return ArticleRealmDB().apply {
        author = this@toRealmArticle.author
        content = this@toRealmArticle.content
        description = this@toRealmArticle.description
        publishedAt = this@toRealmArticle.publishedAt
        url = this@toRealmArticle.url
        urlToImage = this@toRealmArticle.urlToImage

    }

}