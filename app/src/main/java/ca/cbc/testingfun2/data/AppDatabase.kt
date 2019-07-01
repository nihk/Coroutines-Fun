package ca.cbc.testingfun2.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GitHubJob::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gitHubJobsDao(): GitHubJobDao
}