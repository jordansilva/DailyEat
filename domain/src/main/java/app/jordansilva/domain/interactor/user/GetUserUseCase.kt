package app.jordansilva.domain.interactor.user

import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.SimpleUseCaseWithParameter
import app.jordansilva.domain.model.User
import app.jordansilva.domain.repository.UserRepository

class GetUserUseCase(var userRepository: UserRepository) : BaseUseCase(), SimpleUseCaseWithParameter<String, User> {

    override suspend fun execute(userId: String): User {
        return userRepository.getUser(userId)
    }

}