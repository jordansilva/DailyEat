package app.jordansilva.data.repository

import app.jordansilva.data.repository.remote.ApiFeedService
import app.jordansilva.data.util.awaitWithParseError
import app.jordansilva.domain.model.Recipe
import app.jordansilva.domain.repository.RecipeRepository

class RecipeDataRepository(private val api : ApiFeedService) : RecipeRepository {

    override suspend fun getRecipe(recipeId: String): Recipe {
        return api.getRecipe(recipeId).awaitWithParseError()
    }

    override suspend fun getUserRecipes(userId: String): List<Recipe> {
        return api.getUserRecipes(userId).awaitWithParseError()
    }

    override fun saveRecipe(recipe: Recipe): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteRecipeById(recipeId: String): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllRecipes(): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavouriteRecipes(): List<Recipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun markRecipeAsCooked(recipe: Recipe): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}