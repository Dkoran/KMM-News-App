package com.dkoran.newsappkmm.android.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dkoran.newsappkmm.android.R
import com.google.android.material.snackbar.Snackbar


class NewsArticleFragment : Fragment (R.layout.fragment_article) {
    //lateinit var viewModel :NewsViewModel
    private  val RELOAD_COUNT = "reload_count"
    private val RELOAD_ALLOW = 6


 //   val args: NewsArticleFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel =(activity as NewsActivity).viewmodel
        //val article = args.article

//        webView.apply {
//            webChromeClient = object : DefaultWebChromeClient() {
//                override fun onProgressChanged(webView: WebView?, newProgress: Int) {
//                    if (newProgress < 100) {
//                        progressBar.visibility = View.VISIBLE
//                    }
//                    if (newProgress == 100) {
//                        progressBar.visibility = View.GONE
//                    }
//                }
//            }
//            settings.javaScriptEnabled = true
//            settings.allowContentAccess = true
//            settings.domStorageEnabled = true
//           settings.useWideViewPort = true
//            loadUrl(article.url!!)
//        }

//        fab.setOnClickListener {
//          //  viewModel.saveArticle(article)
//            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
//        }
    }
    override fun onAttach(context: Context) {
//        (activity as NewsActivity).hideBottomNavigation()
//        (activity as NewsActivity).hideToolbar()

        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
//        (activity as NewsActivity).showBottomNavigation()
//        (activity as NewsActivity).showToolbar()
    }


    internal open class DefaultWebChromeClient : WebChromeClient()
}