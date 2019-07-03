package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import ca.cbc.testingfun2.util.Resource

interface GitHubJobsRepository {

    suspend fun insertJob(gitHubJob: GitHubJob)

    fun getGitHubJobs(): LiveData<Resource<List<GitHubJob>>>
}