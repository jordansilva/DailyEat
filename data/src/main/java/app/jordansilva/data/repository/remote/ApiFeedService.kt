package app.jordansilva.data.repository.remote

import app.jordansilva.domain.model.Feed
import app.jordansilva.domain.model.Recipe
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiFeedService {

    @GET("feed.json")
    fun getUserFeeds(): Deferred<List<Feed>>

    @GET("recipe/{id}.json")
    fun getRecipe(@Path("id") recipeId : String): Deferred<Recipe>

    @GET("recipe/user/{id}.json")
    fun getUserRecipes(@Path("id") userId : String): Deferred<List<Recipe>>

}