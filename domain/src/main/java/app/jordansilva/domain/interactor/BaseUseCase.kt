package app.jordansilva.domain.interactor

import kotlinx.coroutines.*

abstract class BaseUseCase {

    private val deferredObjects: MutableList<Deferred<*>> = mutableListOf()

    @Synchronized
    protected suspend fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T> {
        val deferred: Deferred<T> = GlobalScope.async { block() }
        deferredObjects.add(deferred)
        deferred.invokeOnCompletion { deferredObjects.remove(deferred) }
        return deferred
    }

    @Synchronized
    protected suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T {
        return async(block).await()
    }

    @Synchronized
    protected fun cancelAllAsync() {
        val deferredObjectsSize = deferredObjects.size

        if (deferredObjectsSize > 0) {
            for (i in deferredObjectsSize - 1 downTo 0) {
                deferredObjects[i].cancel()
            }
        }
    }

    @Synchronized
    open fun cleanup() {
        cancelAllAsync()
    }

}