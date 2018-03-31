package app.jordansilva.domain.interactor

import io.reactivex.Single

interface SingleUseCase<T> {
    fun execute() : Single<T>
}