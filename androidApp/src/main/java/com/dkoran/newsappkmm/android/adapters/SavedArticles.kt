package com.dkoran.newsappkmm.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.models.Article
import kotlinx.android.synthetic.main.item_news_card.view.*

class SavedArticles (private var listener: (Article) -> Unit) : RecyclerView.Adapter<SavedArticles.ArticleViewHolder> (){

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)



    private val difutilCalback = object  : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,difutilCalback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_card,parent,false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articlesItem = differ.currentList[position]

        holder.itemView.apply {
            image.load(articlesItem.urlToImage) {
                crossfade(true)
                placeholder(R.drawable.image_12)
            }

            date.text = articlesItem.publishedAt
            author.text = articlesItem.source?.name
            title.text = articlesItem.title

            setOnClickListener {
                listener(articlesItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}