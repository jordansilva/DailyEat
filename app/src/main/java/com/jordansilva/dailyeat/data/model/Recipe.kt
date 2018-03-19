package com.jordansilva.dailyeat.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(@PrimaryKey var id: String,
                  var name: String,
                  var description: String) : BaseModel() {


    @ForeignKey(entity = User::class, onDelete = ForeignKey.CASCADE, parentColumns = ["id"], childColumns = ["userId"])
    var userId: String? = null

    var ingredients: List<RecipeIngredient>? = null

    //comments, likes, photos, etc
}

data class RecipeIngredient(val name: String,
                            val amount: Float,
                            val amountType: String) : BaseModel()