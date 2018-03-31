package app.jordansilva.domain.interactor

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute() : Completable
}