package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import ca.cbc.testingfun2.util.EspressoIdlingResource
import ca.cbc.testingfun2.util.Resource
import kotlinx.coroutines.delay

// https://developer.android.com/topic/libraries/architecture/coroutines
abstract class NetworkBoundResource<T> {

    fun asLiveData(): LiveData<Resource<T>> {
        EspressoIdlingResource.increment()
        return liveData<Resource<T>> {
            emit(Resource.Loading(null))

            if (shouldFetch(query())) {
                val disposable = emitSource(queryObservable().map { Resource.Loading(it) })

                try {
                    val fetchedData = fetch()
                    // Stop the previous emission to avoid dispatching the saveCallResult as `Resource.Loading`.
                    disposable.dispose()
                    saveCallResult(fetchedData)
                    // Re-establish the emission as `Resource.Success`.
                    emitSource(queryObservable().map { Resource.Success(it) })
                    EspressoIdlingResource.decrement()
                } catch (e: Exception) {
                    onFetchFailed()
                    emitSource(queryObservable().map { Resource.Error(e, it) })
                    EspressoIdlingResource.decrement()
                }
            } else {
                emitSource(queryObservable().map { Resource.Success(it) })
                EspressoIdlingResource.decrement()
            }
        }
    }

    abstract suspend fun query(): T
    abstract fun queryObservable(): LiveData<T>
    abstract suspend fun fetch(): T
    abstract suspend fun saveCallResult(data: T)

    open fun onFetchFailed() {}

    open fun shouldFetch(data: T) = true
}