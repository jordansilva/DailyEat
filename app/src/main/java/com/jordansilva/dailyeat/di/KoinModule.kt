package com.jordansilva.dailyeat.di

import app.jordansilva.data.repository.FeedDataRepository
import app.jordansilva.data.repository.remote.ApiFeedService
import app.jordansilva.data.repository.remote.FeedServiceFactory
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.feed.GetUserFeedsUseCase
import app.jordansilva.domain.model.Feed
import app.jordansilva.domain.repository.FeedRepository
import com.jordansilva.dailyeat.mapper.FeedViewMapper
import com.jordansilva.dailyeat.mapper.MapperView
import com.jordansilva.dailyeat.model.FeedView
import com.jordansilva.dailyeat.ui.feed.FeedViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

object KoinModule {

    val AppModule = applicationContext {
        bean { GetUserFeedsUseCase(get())} bind BaseUseCase::class
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
}