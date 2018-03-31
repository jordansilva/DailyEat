package app.jordansilva.domain.repository

import app.jordansilva.domain.model.Recipe
import io.reactivex.Completable
import io.reactivex.Single

interface RecipeRepository {

    fun getRecipe(recipeId: String): Single<Recipe>

    fun getUserRecipes(userId: String): Single<List<Recipe>>

    fun saveRecipe(recipe: Recipe): Completable

    fun deleteRecipeById(recipeId: String): Completable

    fun deleteAllRecipes(): Completable

    fun getFavouriteRecipes(): Single<List<Recipe>>

    fun markRecipeAsCooked(recipe: Recipe): Completable
}