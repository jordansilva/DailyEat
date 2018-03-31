package app.jordansilva.data.mapper

import app.jordansilva.data.model.RecipeModel
import app.jordansilva.domain.model.Recipe
import app.jordansilva.domain.model.User

open class RecipeMapper : Mapper<Recipe, RecipeModel> {

    override fun mapFromDomain(type: Recipe): RecipeModel {
        return RecipeModel(type.id, type.name, type.description, type.user.id).apply {
            ingredients = type.ingredients
            tags = type.tags
            rating = type.rating
            amountRatings = type.amountRatings

            isLiked = type.liked
            isRated = type.rated
            isFavourite = type.favourited
        }
    }

    override fun mapToDomain(type: RecipeModel): Recipe {
        return Recipe(type.id, type.name, type.description, User(type.userId), type.ingredients).apply {
            ingredients = type.ingredients
            tags = type.tags
            rating = type.rating
            amountRatings = type.amountRatings

            liked = type.isLiked
            rated = type.isRated
            favourited = type.isFavourite
        }
    }

}