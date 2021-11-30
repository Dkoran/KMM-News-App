package com.dkoran.newsappkmm.network

import com.dkoran.newsappkmm.models.NewsData
import com.dkoran.newsappkmm.utils.Constants.Companion.API_KEY
import com.dkoran.newsappkmm.utils.Constants.Companion.BASE_URL
import com.dkoran.newsappkmm.utils.Constants.Companion.News_Path
import com.dkoran.newsappkmm.utils.Constants.Companion.Search_Path
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.get
import io.ktor.http.*
import kotlinx.serialization.json.Json as KotlinJson

class NewsApi {
    private val httpClient: HttpClient = HttpClient {
        install(JsonFeature){
            serializer = KotlinxSerializer(KotlinJson {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun fetchNews(country:String): NewsData {
        val url = URLBuilder(BASE_URL).run {
            path(News_Path)
            parameters.append("country",country)
            parameters.append("apiKey",API_KEY)
            buildString()
        }
        return httpClient.get(url)

    }

    suspend fun searchNews(search:String) : NewsData{
        val url = URLBuilder(BASE_URL+ Search_Path + search).run {
            parameters.append("apiKey",API_KEY)
            buildString()
        }
        return httpClient.get(url)
    }
}