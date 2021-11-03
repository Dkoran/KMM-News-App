package com.dkoran.newsappkmm.android.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.android.adapters.NewsAdapter
import com.dkoran.newsappkmm.network.NewsApi
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment (R.layout.fragment_search_news) {
    lateinit var searchAdapter : NewsAdapter
    private val newsApi = NewsApi()
     val SEARCH_NEWS_TIME_DELAY = 500L


    var job: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        ed_Search.addTextChangedListener {edSearch->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                edSearch?.let {
                    if (edSearch.toString().isNotEmpty()){
                        Log.e("Response 2: ","Rs")

                        kotlin.runCatching {
                            newsApi.searchNews("us",edSearch.toString())
                        }.onSuccess {
                            Log.e("Response Data: ","Rs")

                            hideProgress()
                            Log.e("Response: ",it.toString())
                            searchAdapter.differ.submitList(it.articles)

                        }.onFailure {
                            Log.e("Response Error: ","Rs")
                            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
                            Log.e("Response Error Data:",it.localizedMessage)


                            hideProgress()
                        }
                    }


                }
            }

        }
//        viewModel.searchNews.observe(viewLifecycleOwner, Observer {response ->
//            when(response){
//                is Resource.Success ->{
//                    hideProgress()
//                    response.data?.let {newsResponse->
//                        searchAdapter.differ.submitList(newsResponse.articles)
//                    }
//                }
//                is Resource.Error ->{
//                    hideProgress()
//                    response.message?.let { message ->
//                        Log.e("Error",message)
//                    }
//                }
//                is Resource.Loading ->{
//                    showProgress()
//                }
//            }
//
//        })

    }
    private fun setUpRecyclerView(){
        searchAdapter = NewsAdapter{}
        recyclerView_Search.apply {
            adapter = searchAdapter
        }

    }
    private fun hideProgress(){
        loader_SearchNews.visibility = View.GONE
        recyclerView_Search.visibility = View.VISIBLE
    }
    private fun showProgress(){
        recyclerView_Search.visibility = View.GONE
        loader_SearchNews.visibility = View.VISIBLE

    }
}