package ca.cbc.testingfun2.vm

import androidx.lifecycle.ViewModel
import ca.cbc.testingfun2.data.GitHubJobsRepository
import javax.inject.Inject

class GitHubJobsViewModel @Inject constructor(
    private val repository: GitHubJobsRepository
) : ViewModel() {

    val gitHubJobs = repository.gitHubJobs
}