package com.dkoran.newsappkmm.android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkoran.newsappkmm.android.R
import com.dkoran.newsappkmm.models.ArticleRealmDB
import io.realm.RealmResults
import kotlinx.android.synthetic.main.item_news_card.view.*

class TodoAdapter(val context: Context?, private val todoList: List<ArticleRealmDB>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        holder.itemView.title.text = todoList[position].title





    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news_card, parent, false)
        return ViewHolder(v)


    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}

class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener {
    override fun onClick(v: View?) {


    }

    init {
        itemView.setOnClickListener(this)
    }


  //  val ttitle = itemView.title





}