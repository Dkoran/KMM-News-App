package com.dkoran.newsappkmm.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dkoran.newsappkmm.android.MainActivity
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.android.adapters.TodoAdapter
import com.dkoran.newsappkmm.android.viewmodel.NewsViewModel
import com.dkoran.newsappkmm.models.Article
import com.dkoran.newsappkmm.models.ArticleRealmDB
import kotlinx.android.synthetic.main.fragment_favorite_news.*


class FavoriteNewsFragment :Fragment (R.layout.fragment_favorite_news) {
    lateinit var viewModel : NewsViewModel
    lateinit var newsAdapter: TodoAdapter
    private  var todolist = ArrayList<ArticleRealmDB>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as MainActivity).viewModel






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


          //  newsAdapter.differ.submitList(article)

    }

    private fun setupRecyclerView() {
//        newsAdapter =TodoAdapter(context, viewModel.getSavedArticle())
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