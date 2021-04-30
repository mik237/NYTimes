package com.ibrahim.ny_times_demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.ny_times_demo.databinding.ItemPopularArticleBinding
import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle
import javax.inject.Inject

class PopularArticlesListAdapter @Inject constructor() : RecyclerView.Adapter<PopularArticlesListAdapter.PopularArticlesViewHolder>() {

    private val articlesList = ArrayList<PopularArticle>()
    private var listener : OnItemClickListener? = null


    fun setArticlesList(newArticlesList : List<PopularArticle>){
        val diffCallback = ArticlesListDiffCallback(articlesList, newArticlesList)
        val diffResult   = DiffUtil.calculateDiff(diffCallback)
        articlesList.clear()
        articlesList.addAll(newArticlesList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularArticlesViewHolder {
        val binding = ItemPopularArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularArticlesViewHolder, position: Int) {
        holder.bind(articlesList.get(position))
    }

    override fun getItemCount(): Int = articlesList.size

    inner class PopularArticlesViewHolder(
            private val binding: ItemPopularArticleBinding
    ) :
            RecyclerView.ViewHolder(binding.root){
        fun bind(article : PopularArticle){
            binding.apply {
                tvAuthor.text = article.byline
                tvTitle.text = article.title
                tvPublishDate.text = article.publish_data
                if(article.imageUrl.isNotEmpty()){
                    Glide.with(ivArticleImage)
                            .load(article.imageUrl)
                            .into(ivArticleImage)
                }

                root.setOnClickListener{ listener?.onItemClicked(article) }
            }
        }
    }
}