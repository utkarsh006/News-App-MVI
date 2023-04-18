package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLayoutBinding
import com.example.myapplication.repo.GetNewsModel

class NewsAdapter(private val articles: ArrayList<GetNewsModel.Articles>) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    inner class ArticleViewHolder(private val myItem: ItemLayoutBinding): RecyclerView.ViewHolder(myItem.root){
        fun bind(article: GetNewsModel.Articles) {
            myItem.apply {
                tvSource.text = article.author
                tvDescription.text = article.summary
                tvPublishedAt.text =article.publishedDate
                tvTitle.text = article.title
                root.setOnClickListener {
                    onItemClickListener?.let { it(article) }
                }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    private var onItemClickListener: ((GetNewsModel.Articles) -> Unit)? = null

    fun setonItemClickListener(listener: (GetNewsModel.Articles) -> Unit){
        onItemClickListener = listener
    }
}