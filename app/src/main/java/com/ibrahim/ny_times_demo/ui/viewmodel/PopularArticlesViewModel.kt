package com.ibrahim.ny_times_demo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.ny_times_demo.api.Status
import com.ibrahim.ny_times_demo.api.model.ResponseMostPopularArticles
import com.ibrahim.ny_times_demo.mappers.ViewStateMapperImpl
import com.ibrahim.ny_times_demo.ui.activities.main.MainActivityRepository
import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularArticlesViewModel @Inject constructor(
    private val mainRepo: MainActivityRepository,
    private val viewStateMapper : ViewStateMapperImpl
) : ViewModel() {

    var isTwoPaneView : Boolean = false
    var mappedData: List<PopularArticle> = emptyList()
    val selectedArticle = MutableLiveData<PopularArticle>()


    private val _popularArticlesListLiveData = MutableLiveData<Status<*>>()
    val popularArticlesLiveData : LiveData<Status<*>> = _popularArticlesListLiveData

    fun getNYTimesMostPopularArticles(section: String, period: Int, apiKey: String){

        viewModelScope.launch {
            mainRepo.getNYTimesMostPopularArticles(section, period, apiKey).collect {
                when(it){
                    is Status.Success -> {
                        val data = it.data as ResponseMostPopularArticles
                        mappedData = viewStateMapper.mapArticlesToViewState(data.results)
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