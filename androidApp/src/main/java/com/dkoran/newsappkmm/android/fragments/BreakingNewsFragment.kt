package com.dkoran.newsappkmm.android.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dkoran.newsappkmm.android.MainActivity
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.android.adapters.NewsAdapter
import com.dkoran.newsappkmm.android.viewmodel.NewsViewModel
import com.dkoran.newsappkmm.models.Article
import com.dkoran.newsappkmm.network.NewsApi
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class BreakingNewsFragment :Fragment(R.layout.fragment_breaking_news) {
    private val mainScope = MainScope()
    lateinit var viewModel : NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgress()
        viewModel = (activity as MainActivity).viewModel
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
        mainScope.launch {

            kotlin.runCatching {
                viewModel.news.observe(viewLifecycleOwner,Observer{
                    Log.e("Print Data: ", it.toString())
                    newsAdapter.differ.submitList(it.articles)
                    hideProgress()


                })
            }.onSuccess {
                hideProgress()

            }.onFailure {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("Response Error Data:",it.localizedMessage)


                hideProgress()
            }
        }
    }

    private fun clickedArticle(data: Article) {
        val bundle = Bundle().apply {
            val jsonString = Gson().toJson(data)
            putString("article",jsonString)
            }
        findNavController().navigate(
            R.id.action_breakingNewsFragment_to_newsArticleFragment,bundle
        )

    }

}