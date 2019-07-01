package ca.cbc.testingfun2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.cbc.testingfun2.R
import ca.cbc.testingfun2.data.GitHubJob

class GitHubJobsAdapter : ListAdapter<GitHubJob, GitHubJobViewHolder>(GitHubJobDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubJobViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_github_job, parent, false)
            .let(::GitHubJobViewHolder)
    }

    override fun onBindViewHolder(holder: GitHubJobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}