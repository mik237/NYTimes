package com.ibrahim.ny_times_demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.ny_times_demo.databinding.ItemPopularArticleBinding
import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle
import javax.inject.Inject

class PopularArticlesListAdapter @Inject constructor() : RecyclerView.Adapter<PopularArticlesListAdapter.PopularArticlesViewHolder>() {

    private val popularArticlesList = ArrayList<PopularArticle>()


    fun setArticlesList(popularArticlesList : List<PopularArticle>){
        this.popularArticlesList.clear()
        this.popularArticlesList.addAll(popularArticlesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularArticlesViewHolder {
        val binding = ItemPopularArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularArticlesViewHolder, position: Int) {
        holder.bind(popularArticlesList.get(position))
    }

    override fun getItemCount(): Int = popularArticlesList.size



    class PopularArticlesViewHolder(
        private val binding: ItemPopularArticleBinding
    ) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(article : PopularArticle){
                binding.apply {  }
            }
    }
}