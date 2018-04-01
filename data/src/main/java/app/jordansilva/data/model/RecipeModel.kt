package app.jordansilva.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import app.jordansilva.data.repository.local.converter.RecipeIngredientModelConverter

@Entity(tableName = "recipes", foreignKeys = [(ForeignKey(entity = UserModel::class,
        onDelete = ForeignKey.CASCADE,
        parentColumns = ["id"],
        childColumns = ["userId"]))])
data class RecipeModel(@PrimaryKey val id: String,
                       val name: String,
                       val description: String,
                       val userId: String = "") : BaseModel() {

    @TypeConverters(RecipeIngredientModelConverter::class)
    var ingredients: List<RecipeIngredientModel>? = null

    var tags: ArrayList<String>? = null
    var rating: Float = 0f
    var amountRatings: Int = 0

    var isLiked: Boolean = false
    var isRated: Boolean = false
    var isFavourite: Boolean = false
}