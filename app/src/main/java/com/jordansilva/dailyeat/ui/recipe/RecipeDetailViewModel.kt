package com.jordansilva.dailyeat.ui.recipe

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import app.jordansilva.data.repository.remote.GeneralApiException
import app.jordansilva.domain.interactor.recipe.GetRecipeUseCase
import app.jordansilva.domain.model.Recipe
import com.jordansilva.dailyeat.mapper.MapperView
import com.jordansilva.dailyeat.model.RecipeView
import com.jordansilva.dailyeat.ui.BaseViewModel

/**
 * Created by jordansilva on 18/03/18.
 */
class RecipeDetailViewModel(private val getRecipeUseCase: GetRecipeUseCase,
                            private val mapper: MapperView<RecipeView, Recipe>) : BaseViewModel() {

    var recipe: MutableLiveData<RecipeView> = MutableLiveData()

    companion object {
        val TAG = "RecipeViewModel"
    }

    fun getRecipeById(recipeId: String): MutableLiveData<RecipeView> {
        launchAsync {
            try {
                val result = getRecipeUseCase.execute(recipeId)
                recipe.value = mapper.mapToView(result)
            } catch (e: Exception) {
                when (e) {
                    //is HttpException -> Log.d(TAG, "onError StatusCode: " + e.code())
                    is GeneralApiException -> Log.d(TAG, "onError message: " + e.message())
                    else -> Log.d(TAG, "onError")
                }
            }
        }

        return recipe
    }

}