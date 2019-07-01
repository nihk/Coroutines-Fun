package ca.cbc.testingfun2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_jobs")
data class GitHubJob(
    @PrimaryKey
    val id: String,
    val title: String,
    val company: String
)