package com.dkoran.newsappkmm.android.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.android.adapters.NewsAdapter
import com.dkoran.newsappkmm.models.Article
import com.dkoran.newsappkmm.network.NewsApi
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class BreakingNewsFragment :Fragment(R.layout.fragment_breaking_news) {
    private val mainScope = MainScope()
    private val newsApi = NewsApi()
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Response Main: ","Rs")

        showProgress()
        setUpRecyclerView()
        getNews()

    }
    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter{data -> clickedArticle (data)}
        recyclerViewBreakingNews.apply {
            adapter = newsAdapter
        }

    }
    private fun hideProgress(){
        loader_breakingNews.visibility = View.GONE
        recyclerViewBreakingNews.visibility = View.VISIBLE
    }
    private fun showProgress(){
        recyclerViewBreakingNews.visibility = View.GONE
        loader_breakingNews.visibility = View.VISIBLE

    }

    private fun getNews() {
        Log.e("Response 1: ","Rs")
        mainScope.launch {
            Log.e("Response 2: ","Rs")

            kotlin.runCatching {
                newsApi.fetchNews("us")
            }.onSuccess {
                Log.e("Response Data: ","Rs")

                hideProgress()
                Log.e("Response: ",it.toString())
                newsAdapter.differ.submitList(it.articles)

            }.onFailure {
                Log.e("Response Error: ","Rs")
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("Response Error Data:",it.localizedMessage)


                hideProgress()
            }
        }
    }

    private fun clickedArticle(data: Article) {
        val bundle = Bundle().apply {
                //putSerializable("article", data)
            }
//            findNavController().navigate(
//                R.id.action_breakingNewsFragment_to_newsArticleFragment,
//                bundle
//            )
    }
}