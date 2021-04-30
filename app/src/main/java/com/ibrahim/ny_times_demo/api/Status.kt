package com.ibrahim.ny_times_demo.api

sealed class Status<T>(
    val data: T? = null,
    val errorMsg : String? = null,
    val showLoading : Boolean = false
){
    class Success<T>(data : T) : Status<T>(data)
    class Loading<T>(show: Boolean = false) : Status<T>(showLoading = show)
    class Error<T>(message: String? = "") : Status<T>(errorMsg = message)
}