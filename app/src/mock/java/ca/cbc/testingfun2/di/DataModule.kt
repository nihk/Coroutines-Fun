package ca.cbc.testingfun2.di

import android.content.Context
import androidx.room.Room
import ca.cbc.testingfun2.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object DataModule {

    @AppScope
    @Provides
    @JvmStatic
    fun gitHubJobsDatabase(@AppContext appContext: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun gitHubJobsDao(appDatabase: AppDatabase) = appDatabase.gitHubJobsDao()
}