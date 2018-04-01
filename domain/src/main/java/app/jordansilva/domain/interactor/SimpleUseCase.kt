package app.jordansilva.domain.interactor

interface SimpleUseCase<out T> {
    suspend fun execute(): T
}