package app.jordansilva.data.mapper

import app.jordansilva.data.model.RecipeIngredientModel
import app.jordansilva.domain.model.RecipeIngredient

open class RecipeIngredientMapper : Mapper<RecipeIngredient, RecipeIngredientModel> {
    override fun mapFromDomain(type: RecipeIngredient): RecipeIngredientModel {
        return RecipeIngredientModel(type.name, type.amount, type.amountType)
    }

    override fun mapToDomain(type: RecipeIngredientModel): RecipeIngredient {
        return RecipeIngredient(type.name, type.amount, type.amountType)
    }
}