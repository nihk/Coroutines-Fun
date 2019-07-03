package ca.cbc.testingfun2.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import ca.cbc.testingfun2.data.GitHubJob
import ca.cbc.testingfun2.data.GitHubJobsRepository
import ca.cbc.testingfun2.ui.ScrollAction
import ca.cbc.testingfun2.util.EspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitHubJobsViewModel @Inject constructor(
    private val repository: GitHubJobsRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val refreshJobs = MutableLiveData<Unit>()
    val gitHubJobs = refreshJobs.switchMap {
        repository.getGitHubJobs()
    }

    var pendingScrollAction: ScrollAction = ScrollAction.NONE

    fun insertJob(gitHubJob: GitHubJob) {
        EspressoIdlingResource.increment()
        viewModelScope.launch(dispatcher) {
            repository.insertJob(gitHubJob)
            EspressoIdlingResource.decrement()
        }
    }

    fun fetchGitHubJobs() {
        refreshJobs.value = Unit
    }
}