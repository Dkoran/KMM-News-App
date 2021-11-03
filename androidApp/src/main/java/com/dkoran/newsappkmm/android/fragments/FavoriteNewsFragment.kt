package com.dkoran.newsappkmm.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.models.Article


class FavoriteNewsFragment :Fragment (R.layout.fragment_favorite_news) {
//    lateinit var viewModel :NewsViewModel
//    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel =(activity as NewsActivity).viewmodel

        setupRecyclerView()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
//                val article = newsAdapter.differ.currentList[position]
//                viewModel.deleteArticle(article)
//                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
//                    setAction("Undo") {
//                        viewModel.saveArticle(article)
//                    }
//                    show()
//                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
           // attachToRecyclerView(recyclerViewFavoriteNews)
        }

//        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
//            newsAdapter.differ.submitList(articles)
//        })
    }

    private fun setupRecyclerView() {
//        newsAdapter = NewsAdapter{data ->clickedArticle(data)}
//        recyclerViewFavoriteNews.apply {
//            adapter = newsAdapter
//        }
    }

    private fun clickedArticle(data: Article) {
        val bundle = Bundle().apply {
            //putSerializable("article", data)
        }
//        findNavController().navigate(
//            R.id.action_favoriteNewsFragment_to_newsArticleFragment,
//            bundle
//        )
    }
}