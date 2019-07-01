package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import ca.cbc.testingfun2.di.AppScope
import ca.cbc.testingfun2.util.Resource
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
        return object : NetworkBoundResource<List<GitHubJob>>() {
            override suspend fun query(): List<GitHubJob> {
                return gitHubJobsDao.queryAll()
            }

            override fun queryObservable(): LiveData<List<GitHubJob>> {
                return gitHubJobsDao.queryAllLiveData()
            }

            override suspend fun fetch(): List<GitHubJob> {
                return gitHubJobsService.fetchJobs()
            }

            override suspend fun saveCallResult(data: List<GitHubJob>) {
                gitHubJobsDao.deleteAll()
                gitHubJobsDao.insertAll(data)
            }

        }.asLiveData()
    }
}