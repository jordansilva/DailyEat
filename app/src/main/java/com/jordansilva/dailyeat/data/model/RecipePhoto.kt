package com.jordansilva.dailyeat.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by jordansilva on 18/03/18.
 */
@Entity(tableName = "recipe_photos")
data class RecipePhoto(@PrimaryKey var id: String,
                       var photo: ByteArray,
                       var authorId: String? = null,
                       var author: String? = null) {

    @ForeignKey(entity = Recipe::class, onDelete = ForeignKey.CASCADE, childColumns = ["recipeId"], parentColumns = ["id"])
    var recipeId: String? = null

    var created: Date? = null

}
