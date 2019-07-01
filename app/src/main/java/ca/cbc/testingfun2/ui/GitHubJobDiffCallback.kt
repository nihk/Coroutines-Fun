package ca.cbc.testingfun2.ui

import androidx.recyclerview.widget.DiffUtil
import ca.cbc.testingfun2.data.GitHubJob

object GitHubJobDiffCallback : DiffUtil.ItemCallback<GitHubJob>() {
    override fun areItemsTheSame(oldItem: GitHubJob, newItem: GitHubJob) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: GitHubJob, newItem: GitHubJob) = oldItem == newItem
}