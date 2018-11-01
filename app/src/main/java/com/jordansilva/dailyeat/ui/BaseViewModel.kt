package com.jordansilva.dailyeat.ui

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    private val asyncJobs: MutableList<Job> = mutableListOf()

    @CallSuper
    @Synchronized
    protected fun launchAsync(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = GlobalScope.launch(Dispatchers.Main) { block() }
        asyncJobs.add(job)
        job.invokeOnCompletion { asyncJobs.remove(job) }
    }

    @CallSuper
    @Synchronized
    protected fun cancelAllAsync() {
        val asyncJobsSize = asyncJobs.size

        if (asyncJobsSize > 0) {
            for (i in asyncJobsSize - 1 downTo 0) {
                asyncJobs[i].cancel()
            }
        }
    }

    @CallSuper
    @Synchronized
    open fun cleanup() {
        cancelAllAsync()
    }

    override fun onCleared() {
        cleanup()
        super.onCleared()
    }
}