package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import ca.cbc.testingfun2.util.Resource
import javax.inject.Inject

class GitHubJobsRepositoryImpl @Inject constructor(
    private val gitHubJobsService: GitHubJobsService,
    private val gitHubJobsDao: GitHubJobsDao
) : GitHubJobsRepository {

    override suspend fun insertJob(gitHubJob: GitHubJob) {
        gitHubJobsDao.insert(gitHubJob)
    }

    override fun getGitHubJobs(): LiveData<Resource<List<GitHubJob>>> {
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