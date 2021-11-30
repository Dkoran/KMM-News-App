package com.dkoran.newsappkmm.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkoran.newsappkmm.models.ArticleRealmDB
import com.dkoran.newsappkmm.models.NewsData
import com.dkoran.newsappkmm.network.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()
    val news: MutableLiveData<NewsData> = MutableLiveData()
    val searchNews: MutableLiveData<NewsData> = MutableLiveData()

init {
    getNews("us")
}

    fun getNews(country: String) {
        viewModelScope.launch {
            news.postValue(repository.fetchNews(country))

        }
    }

    fun search(searchQuery: String) = viewModelScope.launch {
        val searchResponse = repository.searchNews(searchQuery)
        searchNews.postValue(searchResponse)
    }

    fun saveArticle(article: ArticleRealmDB){
        repository.addArticle(article)
    }

    fun getSavedArticle(){
        repository.getAllArticle()
    }


}