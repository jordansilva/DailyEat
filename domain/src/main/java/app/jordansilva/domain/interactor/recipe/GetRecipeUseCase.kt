package app.jordansilva.domain.interactor.recipe

import app.jordansilva.domain.interactor.SingleUseCaseWithParameter
import app.jordansilva.domain.model.Recipe
import app.jordansilva.domain.repository.RecipeRepository
import io.reactivex.Single

class GetRecipeUseCase(var recipeRepository: RecipeRepository) : SingleUseCaseWithParameter<String, Recipe> {

    override fun execute(recipeId: String): Single<Recipe> {
        return recipeRepository.getRecipe(recipeId)
    }

}