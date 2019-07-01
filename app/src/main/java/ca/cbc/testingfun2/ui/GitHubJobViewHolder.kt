package ca.cbc.testingfun2.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ca.cbc.testingfun2.data.GitHubJob
import kotlinx.android.synthetic.main.item_github_job.view.*

class GitHubJobViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: GitHubJob) {
        itemView.title.text = item.title
    }
}