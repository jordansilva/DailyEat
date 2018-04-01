package app.jordansilva.domain.interactor.user

import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.SimpleUseCaseWithParameter
import app.jordansilva.domain.model.User

class ValidateUserCredentialsUseCase : BaseUseCase(), SimpleUseCaseWithParameter<User, User?> {


    override suspend fun execute(credentials: User): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}