package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import ca.cbc.testingfun2.di.AppScope
import ca.cbc.testingfun2.util.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

// https://developer.android.com/topic/libraries/architecture/coroutines
@AppScope
class GitHubJobsRepository @Inject constructor(
    private val gitHubJobsService: GitHubJobsService,
    private val gitHubJobsDao: GitHubJobsDao
) {

    suspend fun insertJob(gitHubJob: GitHubJob) {
        gitHubJobsDao.insert(gitHubJob)
    }

    fun getGitHubJobs(): LiveData<Resource<List<GitHubJob>>> {
        return liveData<Resource<List<GitHubJob>>>(Dispatchers.IO) {
            emit(Resource.Loading(null))
            val disposable = emitSource(gitHubJobsDao.queryAllLiveData().map { Resource.Loading(it) })

            try {
                val gitHubJobs = gitHubJobsService.fetchJobs()
                // Stop the previous emission to avoid dispatching the fetched jobs as `loading`.
                disposable.dispose()
                // Update the database.
                gitHubJobsDao.deleteAll()
                gitHubJobsDao.insertAll(gitHubJobs)
                // Re-establish the emission with success type.
                emitSource(gitHubJobsDao.queryAllLiveData().map { Resource.Success(it) })
            } catch (e: Exception) {
                emitSource(gitHubJobsDao.queryAllLiveData().map { Resource.Error(e, it) })
            }
        }
    }
}