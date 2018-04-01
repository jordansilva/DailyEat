package app.jordansilva.domain.interactor


interface SimpleUseCaseWithParameter<in P, out R> {
    suspend fun execute(parameter: P): R
}