package com.dkoran.newsappkmm.android.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dkoran.newsappkmm.android.MainActivity
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.android.viewmodel.NewsViewModel
import com.dkoran.newsappkmm.models.Article
import com.dkoran.newsappkmm.models.ArticleRealmDB
import com.dkoran.newsappkmm.models.toRealmArticle
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_article.*
import java.util.*
import kotlin.random.Random.Default.nextInt


class NewsArticleFragment : Fragment (R.layout.fragment_article) {
    lateinit var viewModel :NewsViewModel
    val args : NewsArticleFragmentArgs by navArgs()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as MainActivity).viewModel
        val articleItem: Article = Gson().fromJson<Article>(args.article)

        webView.apply {
            webChromeClient = object : DefaultWebChromeClient() {
                override fun onProgressChanged(webView: WebView?, newProgress: Int) {
                    if (newProgress < 100) {
                        progressBar?.visibility = View.VISIBLE
                    }
                    if (newProgress == 100) {
                        progressBar?.visibility = View.GONE
                    }
                }
            }
            settings.javaScriptEnabled = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
           settings.useWideViewPort = true
            loadUrl(articleItem.url!!)
        }

        fab.setOnClickListener {


            Log.e("Real DB 2:", "Init")
            val articledb = ArticleRealmDB()
            val randomNumber = (0..1000).random()
            articledb.author = articleItem.author
            articledb.title = articleItem.title
            articledb.urlToImage = articleItem.urlToImage
            articledb.url = articleItem.url
            articledb.description = articleItem.description
            articledb.content = articleItem.content
            articledb.publishedAt = articleItem.publishedAt
            articledb.id = randomNumber
            viewModel.saveArticle(articledb)

            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
    override fun onAttach(context: Context) {
        (activity as MainActivity).hideBottomNavigation()

        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
       (activity as MainActivity).showBottomNavigation()

    }


    internal open class DefaultWebChromeClient : WebChromeClient()
    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)

}