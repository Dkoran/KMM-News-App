package com.dkoran.newsappkmm.network

import com.dkoran.newsappkmm.database.RealmDatabase
import com.dkoran.newsappkmm.models.ArticleRealmDB
import com.dkoran.newsappkmm.models.NewsData

class NewsRepository {
    val api = NewsApi()
    val database = RealmDatabase()

    suspend fun fetchNews(country:String): NewsData {

        return api.fetchNews(country)
    }

    suspend fun searchNews(search:String) : NewsData{
        return api.searchNews(search)
    }

    fun addArticle(article:ArticleRealmDB){
        database.addArticle(article)
    }

    fun deleteArticle(title : String){
        database.deleteArticle(title)
    }

    fun delateAllArticle(){
        database.clearAllArticle()
    }
     fun getAllArticle() : List<ArticleRealmDB>{
       return database.getAllArticles()
     }

}