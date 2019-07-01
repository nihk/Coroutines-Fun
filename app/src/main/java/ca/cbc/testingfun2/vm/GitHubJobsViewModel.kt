package ca.cbc.testingfun2.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import ca.cbc.testingfun2.data.GitHubJob
import ca.cbc.testingfun2.data.GitHubJobsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitHubJobsViewModel @Inject constructor(
    private val repository: GitHubJobsRepository
) : ViewModel() {

    private val refreshJobs = MutableLiveData<Unit>()
    val gitHubJobs = refreshJobs.switchMap {
        repository.getGitHubJobs()
    }

    fun insertJob(gitHubJob: GitHubJob) {
        viewModelScope.launch {
            repository.insertJob(gitHubJob)
        }
    }

    fun fetchGitHubJobs() {
        refreshJobs.value = Unit
    }
}