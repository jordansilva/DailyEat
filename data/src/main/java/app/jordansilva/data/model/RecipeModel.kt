package app.jordansilva.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import app.jordansilva.domain.model.RecipeIngredient
import app.jordansilva.domain.model.User

@Entity(tableName = "recipes", foreignKeys = [(ForeignKey(entity = User::class,
        onDelete = ForeignKey.CASCADE,
        parentColumns = ["id"],
        childColumns = ["userId"]))])
data class RecipeModel(@PrimaryKey val id: String,
                       val name: String,
                       val description: String,
                       val userId: String = "") : BaseModel() {

    var ingredients: List<RecipeIngredient>? = null

    var tags: List<String>? = null
    var rating: Float = 0f
    var amountRatings: Int = 0

    var isLiked: Boolean = false
    var isRated: Boolean = false
    var isFavourite: Boolean = false
}