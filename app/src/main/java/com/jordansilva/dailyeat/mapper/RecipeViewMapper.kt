package com.jordansilva.dailyeat.mapper

import app.jordansilva.domain.model.Recipe
import com.jordansilva.dailyeat.model.RecipeView

class RecipeViewMapper : MapperView<RecipeView, Recipe> {

    override fun mapToView(type: Recipe): RecipeView {

        return RecipeView(type.id,
                type.name,
                type.description,
                type.imageUrl,
                type.userId,
                type.userAvatar,
                type.userName,
                null).apply {

            //TODO: Create ingredients mapper
            //ingredients = type.ingredients
            tags = type.tags
            rating = type.rating
            amountRatings = type.amountRatings
            cooked = type.cooked
            liked = type.liked
            rated = type.rated
            favourited = type.favourited

        }
    }

}