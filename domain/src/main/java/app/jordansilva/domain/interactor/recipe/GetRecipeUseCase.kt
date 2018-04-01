package app.jordansilva.domain.interactor.recipe

import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.SimpleUseCaseWithParameter
import app.jordansilva.domain.model.Recipe
import app.jordansilva.domain.repository.RecipeRepository

class GetRecipeUseCase(var recipeRepository: RecipeRepository) : BaseUseCase(), SimpleUseCaseWithParameter<String, Recipe> {

    override suspend fun execute(recipeId: String): Recipe {
        return recipeRepository.getRecipe(recipeId)
    }

}