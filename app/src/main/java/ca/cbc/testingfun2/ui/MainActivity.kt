package ca.cbc.testingfun2.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import ca.cbc.testingfun2.R
import ca.cbc.testingfun2.data.GitHubJob
import ca.cbc.testingfun2.util.Resource
import ca.cbc.testingfun2.vm.GitHubJobsViewModel
import ca.cbc.testingfun2.vm.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: GitHubJobsViewModel by viewModels { viewModelFactory }
    private val adapter = GitHubJobsAdapter()

    companion object {
        var id = 666
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter

        observeGitHubJobs()
        viewModel.fetchGitHubJobs()

        button.setOnClickListener {
            val gitHubJob = GitHubJob(
                id = id++.toString(),
                title = "A title",
                company = "A company"
            )
            viewModel.insertJob(gitHubJob)
        }

        refresh.setOnClickListener {
            viewModel.fetchGitHubJobs()
        }
    }

    private fun observeGitHubJobs() {
        viewModel.gitHubJobs.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    it.data?.let(adapter::submitList)
                }
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Failed to fetch new Jobs", Toast.LENGTH_LONG)
                        .show()
                    adapter.submitList(it.data)
                }
            }
        })
    }
}