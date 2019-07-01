package ca.cbc.testingfun2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GitHubJobDao {

    @Insert
    suspend fun insert(gitHubJobs: List<GitHubJob>)

    @Query("SELECT * FROM github_jobs")
    suspend fun queryAll(): List<GitHubJob>
}