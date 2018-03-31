package app.jordansilva.domain.interactor.recipe

import app.jordansilva.domain.interactor.SingleUseCaseWithParameter
import app.jordansilva.domain.model.Recipe
import app.jordansilva.domain.repository.RecipeRepository
import io.reactivex.Single

class GetUserRecipes(var recipeRepository: RecipeRepository) : SingleUseCaseWithParameter<String, List<Recipe>> {

    override fun execute(userId: String): Single<List<Recipe>> {
        return recipeRepository.getUserRecipes(userId)
    }

}