package com.ibrahim.ny_times_demo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.ibrahim.ny_times_demo.api.Status
import com.ibrahim.ny_times_demo.api.model.ResponseMostPopularArticles
import com.ibrahim.ny_times_demo.mappers.ViewStateMapperImpl
import com.ibrahim.ny_times_demo.ui.activities.main.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularArticlesViewModel @Inject constructor(
    private val mainRepo: MainActivityRepository,
    private val viewStateMapper : ViewStateMapperImpl
) : ViewModel() {

    private val _popularArticlesListLiveData = MutableLiveData<Status<*>>()
    val popularArticlesLiveData : LiveData<Status<*>> = _popularArticlesListLiveData


    fun getNYTimesMostPopularArticles(section: String, period: Int, apiKey: String){

        viewModelScope.launch {
            mainRepo.getNYTimesMostPopularArticles(section, period, apiKey).collect {
                when(it){
                    is Status.Success -> {
                        val data = it.data as ResponseMostPopularArticles
                        val mappedData = viewStateMapper.mapArticlesToViewState(data.results)
                        _popularArticlesListLiveData.value = Status.Success(mappedData)
                    }
                    else -> {
                        _popularArticlesListLiveData.value = it
                    }
                }
            }
        }
    }
}