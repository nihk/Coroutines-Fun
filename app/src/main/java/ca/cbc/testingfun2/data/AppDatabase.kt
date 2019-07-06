package ca.cbc.testingfun2.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GitHubJob::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_database.db"
    }

    abstract fun gitHubJobsDao(): GitHubJobsDao
}