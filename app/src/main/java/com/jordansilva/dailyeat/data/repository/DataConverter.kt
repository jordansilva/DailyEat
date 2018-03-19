package com.jordansilva.dailyeat.data.repository

import android.arch.persistence.room.TypeConverter
import com.jordansilva.dailyeat.data.model.RecipeIngredient
import unimedbh.app.prestador.util.fromJson
import unimedbh.app.prestador.util.toJson
import java.util.*


/**
 * Created by jordansilva on 18/03/18.
 */
class DataConverter {
    @TypeConverter
    fun fromRecipeIngredient(recipeIngredient: List<RecipeIngredient>?): String? {
        if (recipeIngredient != null)
            return toJson(recipeIngredient, RecipeIngredient::class.java)

        return null
    }

    @TypeConverter
    fun toRecipeIngredient(recipeIngredient: String?): List<RecipeIngredient>? {
        if (recipeIngredient != null)
            return fromJson(recipeIngredient, RecipeIngredient::class.java)

        return null
    }

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toLong(value: Date?): Long? {
        return (value?.time)!!.toLong()
    }
}