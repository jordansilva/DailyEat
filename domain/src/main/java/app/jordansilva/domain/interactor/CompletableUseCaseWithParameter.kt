package app.jordansilva.domain.interactor

import io.reactivex.Completable

interface CompletableUseCaseWithParameter<P> {
    fun execute(parameter : P) : Completable
}