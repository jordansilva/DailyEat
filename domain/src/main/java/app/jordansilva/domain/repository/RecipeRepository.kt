package app.jordansilva.domain.repository

import app.jordansilva.domain.model.Recipe

interface RecipeRepository {

    suspend fun getRecipe(recipeId: String): Recipe

    suspend fun getUserRecipes(userId: String): List<Recipe>

    fun saveRecipe(recipe: Recipe): Void

    fun deleteRecipeById(recipeId: String): Void

    fun deleteAllRecipes(): Void

    fun getFavouriteRecipes(): List<Recipe>

    fun markRecipeAsCooked(recipe: Recipe): Void
}