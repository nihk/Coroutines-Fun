package ca.cbc.testingfun2.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GitHubJobsViewModel : ViewModel() {

    fun getJobs() {
        viewModelScope.launch {

        }
    }
}