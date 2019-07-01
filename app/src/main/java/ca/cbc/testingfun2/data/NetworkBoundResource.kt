package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import ca.cbc.testingfun2.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

abstract class NetworkBoundResource<T> {

    fun asLiveData(): LiveData<Resource<T>> {
        return liveData<Resource<T>>(Dispatchers.IO) {
            emit(Resource.Loading(null))

            if (shouldFetch(query())) {
                val disposable = emitSource(queryObservable().map { Resource.Loading(it) })

                try {
                    val fetchedData = fetch()
                    // Stop the previous emission to avoid dispatching the fetched data as `loading`.
                    disposable.dispose()
                    saveCallResult(fetchedData)
                    // Re-establish the emission with success type.
                    emitSource(queryObservable().map { Resource.Success(it) })
                } catch (e: Exception) {
                    onFetchFailed()
                    emitSource(queryObservable().map { Resource.Error(e, it) })
                }
            } else {
                emitSource(queryObservable().map { Resource.Success(it) })
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