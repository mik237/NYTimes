package com.ibrahim.ny_times_demo.adapters

import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle

interface OnItemClickListener {
    fun onItemClicked(article : PopularArticle)
}