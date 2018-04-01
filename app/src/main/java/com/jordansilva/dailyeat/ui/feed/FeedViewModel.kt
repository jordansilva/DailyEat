package com.jordansilva.dailyeat.ui.feed

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import app.jordansilva.domain.interactor.feed.GetUserFeedsUseCase
import app.jordansilva.domain.model.Feed
import com.jordansilva.dailyeat.mapper.MapperView
import com.jordansilva.dailyeat.model.FeedView
import com.jordansilva.dailyeat.ui.BaseViewModel

/**
 * Created by jordansilva on 18/03/18.
 */
class FeedViewModel(val feedsUseCase: GetUserFeedsUseCase, val mapper: MapperView<FeedView, Feed>) : BaseViewModel() {

    private lateinit var feeds: MutableLiveData<List<FeedView>>

    init {
        fetchFeeds()
    }

    fun getFeeds(): LiveData<List<FeedView>> {
        return feeds
    }

    fun fetchFeeds() {
        launchAsync {
            val result = feedsUseCase.execute().await()
            feeds.value = result.map { mapper.mapToView(it) }
        }
    }

}