package com.jordansilva.dailyeat.di

import app.jordansilva.data.repository.FeedDataRepository
import app.jordansilva.data.repository.remote.ApiFeedService
import app.jordansilva.data.repository.remote.FeedServiceFactory
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.feed.GetUserFeedsUseCase
import app.jordansilva.domain.model.Feed
import app.jordansilva.domain.repository.FeedRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jordansilva.dailyeat.mapper.FeedViewMapper
import com.jordansilva.dailyeat.mapper.MapperView
import com.jordansilva.dailyeat.model.FeedView
import com.jordansilva.dailyeat.ui.feed.FeedViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

object KoinModule {

    val AppModule = applicationContext {
        bean { GetUserFeedsUseCase(get())} bind BaseUseCase::class

        bean { makeGson() } bind Gson::class
    }

    val ViewModule = applicationContext {
        viewModel { FeedViewModel(get(), get()) }
        factory { FeedViewMapper() as MapperView<FeedView, Feed> }
    }

    val RepositoryModule = applicationContext {
        bean { FeedDataRepository(get()) as FeedRepository }
    }

    val ApiModule = applicationContext {
        bean { FeedServiceFactory.makeFeedService(false) } bind ApiFeedService::class
    }

    //TODO: Create this in somewhere else
    fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}