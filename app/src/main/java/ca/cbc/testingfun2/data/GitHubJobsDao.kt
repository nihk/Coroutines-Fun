package ca.cbc.testingfun2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GitHubJobsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gitHubJobs: List<GitHubJob>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gitHubJob: GitHubJob)

    @Query("SELECT * FROM github_jobs ORDER BY title")
    fun queryAllLiveData(): LiveData<List<GitHubJob>>

    @Query("DELETE FROM github_jobs")
    suspend fun deleteAll()
}