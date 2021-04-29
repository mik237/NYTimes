package com.ibrahim.ny_times_demo.ui.fragments.models

import com.ibrahim.ny_times_demo.util.empty

data class PopularArticle(
    val id: Long,
    val publish_data : String = String.empty,
    val url: String = String.empty,
    val byline: String = String.empty,
    val title: String = String.empty,
    val imageUrl: String = String.empty
)