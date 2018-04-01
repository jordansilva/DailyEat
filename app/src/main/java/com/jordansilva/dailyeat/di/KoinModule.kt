package com.jordansilva.dailyeat.di

import app.jordansilva.data.repository.FeedDataRepository
import app.jordansilva.data.repository.RecipeDataRepository
import app.jordansilva.data.repository.remote.ApiFeedService
import app.jordansilva.data.repository.remote.FeedServiceFactory
import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.feed.GetUserFeedsUseCase
import app.jordansilva.domain.interactor.recipe.GetRecipeUseCase
import app.jordansilva.domain.model.Feed
import app.jordansilva.domain.model.Recipe
import app.jordansilva.domain.repository.FeedRepository
import app.jordansilva.domain.repository.RecipeRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jordansilva.dailyeat.mapper.FeedViewMapper
import com.jordansilva.dailyeat.mapper.MapperView
import com.jordansilva.dailyeat.mapper.RecipeViewMapper
import com.jordansilva.dailyeat.model.FeedView
import com.jordansilva.dailyeat.model.RecipeView
import com.jordansilva.dailyeat.ui.feed.FeedViewModel
import com.jordansilva.dailyeat.ui.recipe.RecipeDetailViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

object KoinModule {

    val AppModule = applicationContext {
        bean { GetUserFeedsUseCase(get()) } bind BaseUseCase::class
        bean { GetRecipeUseCase(get()) }

        bean { makeGson() } bind Gson::class
    }

    val ViewModule = applicationContext {
        context("Feed") {
            viewModel { FeedViewModel(get(), get()) }
            factory { FeedViewMapper() as MapperView<FeedView, Feed> }
        }

        context("RecipeDetail") {
            viewModel { RecipeDetailViewModel(get(), get()) }
            factory { RecipeViewMapper() as MapperView<RecipeView, Recipe> }
        }
    }

    val RepositoryModule = applicationContext {
        bean { FeedDataRepository(get()) as FeedRepository }
        bean { RecipeDataRepository(get()) as RecipeRepository }
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