package com.jordansilva.dailyeat.data.repository.local

import android.arch.persistence.room.*
import com.jordansilva.dailyeat.data.model.Recipe

/**
 * Created by jordansilva on 18/03/18.
 */
@Dao interface RecipesDao {

    /**
     * Get list of recipes by user id.
     *
     * @param userId the user id.
     * @return list of recipes.
     */
    @Query("SELECT * FROM Recipes WHERE userId = :userId") fun getRecipesByUser(userId: String): List<Recipe>?

    /**
     * Insert a recipe in the database. If the recipe already exists, replace it.
     *
     * @param recipe to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertRecipe(recipe: Recipe)

    /**
     * Update a recipe.
     *
     * @param recipe recipe to be updated
     * @return the number of recipes updated. This should always be 1.
     */
    @Update fun updateRecipe(recipe: Recipe): Int


    /**
     * Delete a recipe by id.
     *
     * @param recipeId the recipe id.
     * @return the number of recipes deleted. This should always be 1.
     */
    @Query("DELETE FROM Recipes WHERE id = :recipeId") fun deleteRecipeById(recipeId: String): Int

    /**
     * Delete all recipes.
     */
    @Query("DELETE FROM Recipes") fun deleteAllRecipes()

}