package com.ibrahim.ny_times_demo.ui.activities.main

import com.ibrahim.ny_times_demo.api.NetworkService
import com.ibrahim.ny_times_demo.api.model.ResponseMostPopularArticles
import com.ibrahim.ny_times_demo.api.networkBoundResource
import javax.inject.Inject

class MainActivityRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNYTimesMostPopularArticles(
            section: String,
            period: Int,
            apiKey: String
    ) = networkBoundResource {
        networkService.getNYTimesMostPopularArticles(
                section,
                period,
                apiKey
        )
    }
}