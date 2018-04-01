package com.jordansilva.dailyeat.ui.feed

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import app.jordansilva.data.repository.remote.GeneralApiException
import app.jordansilva.domain.interactor.feed.GetUserFeedsUseCase
import app.jordansilva.domain.model.Feed
import com.jordansilva.dailyeat.mapper.MapperView
import com.jordansilva.dailyeat.model.FeedView
import com.jordansilva.dailyeat.ui.BaseViewModel
import retrofit2.HttpException

/**
 * Created by jordansilva on 18/03/18.
 */
class FeedViewModel(val feedsUseCase: GetUserFeedsUseCase, val mapper: MapperView<FeedView, Feed>) : BaseViewModel() {

    private var feeds: MutableLiveData<List<FeedView>> = MutableLiveData()

    init {
        fetchFeeds()
    }

    companion object {
        val TAG = "FeedViewModel"
    }

    fun getFeeds(): LiveData<List<FeedView>> {
        return feeds
    }

    fun fetchFeeds() {
        launchAsync {
            try {
                val result = feedsUseCase.execute().await()
                feeds.value = result.map { mapper.mapToView(it) }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> Log.d(TAG, "onError StatusCode: " + e.code())
                    is GeneralApiException -> Log.d(TAG, "onError message: " + e.message())
                    else -> Log.d(TAG, "onError")
                }
            }
        }
    }

}