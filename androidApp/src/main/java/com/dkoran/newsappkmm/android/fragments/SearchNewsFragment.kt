package com.dkoran.newsappkmm.android.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dkoran.newsappkmm.android.MainActivity
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.android.adapters.NewsAdapter
import com.dkoran.newsappkmm.android.viewmodel.NewsViewModel
import com.dkoran.newsappkmm.network.NewsApi
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment (R.layout.fragment_search_news) {
    lateinit var searchAdapter : NewsAdapter
    lateinit var viewModel : NewsViewModel
     val SEARCH_NEWS_TIME_DELAY = 500L
      var job: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
        ed_Search.addTextChangedListener {edSearch->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                edSearch?.let {
                    if (edSearch.toString().isNotEmpty()){

                        kotlin.runCatching {
                            viewModel.search(edSearch.toString())
                        }.onSuccess {
                            Log.e("Success: ","Called.")
                            hideProgress()
                            Log.e("Response: ",it.toString())
                            viewModel.searchNews.observe(viewLifecycleOwner, Observer{ data ->
                                searchAdapter.differ.submitList(data.articles)

                            })

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