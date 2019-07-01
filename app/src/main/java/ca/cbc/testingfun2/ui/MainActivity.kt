package ca.cbc.testingfun2.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import ca.cbc.testingfun2.R
import ca.cbc.testingfun2.util.Resource
import ca.cbc.testingfun2.vm.GitHubJobsViewModel
import ca.cbc.testingfun2.vm.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<GitHubJobsViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.gitHubJobs.observe(this, Observer {
            when (it) {
                is Resource.Loading -> Log.d("asdf", "Loading: ${it.data?.size}")
                is Resource.Success -> Log.d("asdf", "Success: ${it.data?.size}")
                is Resource.Error -> Log.d("asdf", "Error: ${it.data?.size}", it.throwable)
            }
        })
    }
}