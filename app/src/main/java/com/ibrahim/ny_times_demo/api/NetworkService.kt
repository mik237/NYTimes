package com.ibrahim.ny_times_demo.api

import com.ibrahim.ny_times_demo.api.model.ResponseMostPopularArticles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("mostviewed/{section}/{period}.json")
    suspend fun getNYTimesMostPopularArticles(
        @Path("section") section: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ) : Response<ResponseMostPopularArticles>

}