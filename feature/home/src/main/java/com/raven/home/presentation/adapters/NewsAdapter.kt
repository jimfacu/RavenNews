package com.raven.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.raven.home.data.Model.News

class NewsAdapter(private val listener:OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = mutableListOf<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding, this)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem =newsList[position]
        holder.bind(currentItem)
    }

    fun setNewProductList(data: List<News>) {
        newsList.clear()
        newsList.addAll(data)
        notifyItemRangeChanged(0, data.size)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding, private val adapter: NewsAdapter) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Vincula los datos del producto al ViewHolder.
         *
         * @param product El objeto que contiene los datos del producto.
         */
        fun bind(news: News) {
            binding.title.text = news.title
            binding.byline = news.byline
            binding.published_date = news.published_date
            binding.abstract = news.abstract


            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val newsItem = adapter.newsList[position]
                    adapter.listener.onItemClick(newsItem)
                }
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(news: News)
    }

}

