package app.jordansilva.domain.interactor.user

import app.jordansilva.domain.interactor.SingleUseCaseWithParameter
import app.jordansilva.domain.model.User
import io.reactivex.Single

class ValidateUserCredentialsUseCase : SingleUseCaseWithParameter<User, User?> {


    override fun execute(credentials: User): Single<User?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}