package com.ibrahim.ny_times_demo.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle

class ArticlesListDiffCallback(private val oldList: List<PopularArticle>, private val newList: List<PopularArticle>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}