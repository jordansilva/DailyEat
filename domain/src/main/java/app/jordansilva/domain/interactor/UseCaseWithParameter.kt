package app.jordansilva.domain.interactor

import io.reactivex.Observable

interface UseCaseWithParameter<P, R> {
    fun execute(parameter: P): Observable<R>
}