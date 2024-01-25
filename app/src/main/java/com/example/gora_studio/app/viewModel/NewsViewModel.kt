package com.example.gora_studio.app.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gora_studio.const.APPLE
import com.example.gora_studio.const.BITCOIN
import com.example.gora_studio.const.SORTBY
import com.example.gora_studio.const.TECHNOLOGIES
import com.example.gora_studio.data.network.model.NewsResponseModel
import com.example.gora_studio.domain.usecase.GetNewsByQueryAndDateUseCase
import com.example.gora_studio.utils.getThisWeekPeriod
import com.example.gora_studio.app.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsByQueryAndDateUseCase: GetNewsByQueryAndDateUseCase
) : ViewModel() {

    private val _newsDataBitcoin = MutableLiveData<ResultStatus<NewsResponseModel>>()
    val newsDataBitcoin: LiveData<ResultStatus<NewsResponseModel>> get() = _newsDataBitcoin

    private val _newsDataApple = MutableLiveData<ResultStatus<NewsResponseModel>>()
    val newsDataApple: LiveData<ResultStatus<NewsResponseModel>> get() = _newsDataApple

    private val _newsDataTechnologies = MutableLiveData<ResultStatus<NewsResponseModel>>()
    val newsDataTechnologies: LiveData<ResultStatus<NewsResponseModel>> get() = _newsDataTechnologies

    private val newsLiveDataList = listOf(
        NewsLiveDataWrapper(_newsDataBitcoin, BITCOIN),
        NewsLiveDataWrapper(_newsDataApple, APPLE),
        NewsLiveDataWrapper(_newsDataTechnologies, TECHNOLOGIES)
    )

    fun getLatestNews() {
        viewModelScope.launch(Dispatchers.IO) {
            newsLiveDataList.forEach { wrapper ->
                try {
                    val (startOfWeek, endOfWeek) = getThisWeekPeriod()
                    val result = getNewsByQueryAndDateUseCase.execute(
                        wrapper.query, startOfWeek, endOfWeek,
                        SORTBY
                    )
                    wrapper.newsData.postValue(result)
                } catch (e: Exception) {
                   Log.e("Error ViewModel", "Error corutines")
                }
            }
        }
    }
}


