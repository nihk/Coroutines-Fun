package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ca.cbc.testingfun2.util.Resource
import javax.inject.Inject

class GitHubJobsRepository @Inject constructor(
    val gitHubJobsService: GitHubJobsService,
    val gitHubJobDao: GitHubJobDao
) {

    val gitHubJobs: LiveData<Resource<List<GitHubJob>>> = liveData {
        emit(Resource.Loading<List<GitHubJob>>(null))
        emit(Resource.Loading(gitHubJobDao.queryAll()))
        try {
            val gitHubJobs = gitHubJobsService.fetchJobs()
            gitHubJobDao.insert(gitHubJobs)
            emit(Resource.Success(gitHubJobDao.queryAll()))
        } catch (e: Exception) {
            emit(Resource.Error<List<GitHubJob>>(e, gitHubJobDao.queryAll()))
        }
    }
}