package com.ibrahim.ny_times_demo.ui.activities.main

import com.ibrahim.ny_times_demo.api.NetworkService
import com.ibrahim.ny_times_demo.api.model.ResponseMostPopularArticles
import com.ibrahim.ny_times_demo.api.networkBoundResource
import com.ibrahim.ny_times_demo.util.ConnectionManager
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
        private val networkService: NetworkService,
        private val connectionManager: ConnectionManager) {

    fun getNYTimesMostPopularArticles(
            section: String,
            period: Int,
            apiKey: String
    ) = networkBoundResource(
            isNetworkConnected = {
                connectionManager.isConnected()
            },
            fetch = {
                networkService.getNYTimesMostPopularArticles(
                        section,
                        period,
                        apiKey
                )
            })
}