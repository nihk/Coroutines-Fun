package ca.cbc.testingfun2.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import ca.cbc.testingfun2.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(
    includes = [
        ViewModelModule::class
    ]
)
object AppModule {

    @Reusable
    @Provides
    @AppContext
    @JvmStatic
    fun appContext(application: Application) = application.applicationContext

    @Reusable
    @Provides
    @JvmStatic
    fun sharedPreferences(@AppContext appContext: Context): SharedPreferences = with(appContext) {
        return@sharedPreferences getSharedPreferences("${packageName}_preferences", Context.MODE_PRIVATE)
    }

    @AppScope
    @Provides
    @JvmStatic
    fun gitHubJobsDatabase(@AppContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "app_database.db")
            .build()
    }

    @Reusable
    @Provides
    @JvmStatic
    fun gitHubJobsDao(appDatabase: AppDatabase) = appDatabase.gitHubJobsDao()
}