package com.ibrahim.ny_times_demo.mappers

import com.ibrahim.ny_times_demo.api.model.Result
import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle

interface ViewStateMapper {
    fun mapArticlesToViewState(results: List<Result>) : List<PopularArticle>
}