package com.ibrahim.ny_times_demo.api

import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <T>networkBoundResource(
        crossinline isNetworkConnected : suspend () -> Boolean,
    crossinline fetch: suspend () -> Response<T>
) = flow {


    if(isNetworkConnected()) {
        emit(Status.Loading(true))
        val response = fetch()
        emit(Status.Loading(false))
        if(response.isSuccessful && response.body() != null){
            emit(Status.Success(response.body()))
        }
        else {
            emit(Status.Error<String>(response.message()))
        }
    }
    else{
        emit(Status.Error<String>("Not Connected to Internet!"))
    }


}