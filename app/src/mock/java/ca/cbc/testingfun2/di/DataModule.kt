package ca.cbc.testingfun2.di

import android.content.Context
import androidx.room.Room
import ca.cbc.testingfun2.data.AppDatabase
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    @JvmStatic
    fun gitHubJobsDatabase(@AppContext appContext: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @JvmStatic
    fun gitHubJobsDao(appDatabase: AppDatabase) = appDatabase.gitHubJobsDao()
}