package app.jordansilva.data.repository.local.converter

import android.arch.persistence.room.TypeConverter
import app.jordansilva.data.model.RecipeIngredientModel
import app.jordansilva.data.util.fromJson
import app.jordansilva.data.util.toJson

/**
 * Created by jordansilva on 18/03/18.
 */
class RecipeIngredientModelConverter {
    @TypeConverter
    fun fromRecipeIngredientModel(recipeIngredient: List<RecipeIngredientModel>?): String? {
        if (recipeIngredient != null)
            return toJson(recipeIngredient, RecipeIngredientModel::class.java)

        return null
    }

    @TypeConverter
    fun toRecipeIngredientModel(recipeIngredient: String?): List<RecipeIngredientModel>? {
        if (recipeIngredient != null)
            return fromJson(recipeIngredient, RecipeIngredientModel::class.java)

        return null
    }
}