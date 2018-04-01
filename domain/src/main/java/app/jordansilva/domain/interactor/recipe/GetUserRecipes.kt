package app.jordansilva.domain.interactor.recipe

import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.SimpleUseCaseWithParameter
import app.jordansilva.domain.model.Recipe
import app.jordansilva.domain.repository.RecipeRepository

class GetUserRecipes(var recipeRepository: RecipeRepository) : BaseUseCase(), SimpleUseCaseWithParameter<String, List<Recipe>> {

    override suspend fun execute(userId: String): List<Recipe> {
        return recipeRepository.getUserRecipes(userId)
    }

}