package app.jordansilva.domain.interactor

import io.reactivex.Single

interface SingleUseCaseWithParameter<P, R> {
    fun execute(parameter: P): Single<R>
}