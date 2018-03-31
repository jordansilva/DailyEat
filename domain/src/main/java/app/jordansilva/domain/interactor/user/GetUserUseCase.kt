package app.jordansilva.domain.interactor.user

import app.jordansilva.domain.interactor.SingleUseCaseWithParameter
import app.jordansilva.domain.model.User
import app.jordansilva.domain.repository.UserRepository
import io.reactivex.Single

class GetUserUseCase(var userRepository: UserRepository) : SingleUseCaseWithParameter<String, User> {

    override fun execute(userId: String): Single<User> {
        return userRepository.getUser(userId)
    }

}