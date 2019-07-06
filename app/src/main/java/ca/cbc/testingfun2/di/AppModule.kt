package ca.cbc.testingfun2.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(
    includes = [
        ViewModelModule::class,
        NetworkingModule::class,
        RepositoryModule::class,
        DataModule::class
    ]
)
object AppModule {

    @Reusable
    @Provides
    @AppContext
    @JvmStatic
    fun appContext(application: Application): Context = application.applicationContext

    @Reusable
    @Provides
    @JvmStatic
    fun sharedPreferences(@AppContext appContext: Context): SharedPreferences = with(appContext) {
        return@sharedPreferences getSharedPreferences(
            "${packageName}_preferences",
            Context.MODE_PRIVATE
        )
    }
}