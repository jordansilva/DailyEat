package app.jordansilva.domain.interactor

import kotlinx.coroutines.experimental.Deferred

interface CancelableUseCase<out T> {
    suspend fun execute(): Deferred<T>
}