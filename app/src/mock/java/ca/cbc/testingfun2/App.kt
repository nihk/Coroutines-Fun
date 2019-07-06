package ca.cbc.testingfun2

import ca.cbc.testingfun2.di.AppComponent
import ca.cbc.testingfun2.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AppComponent {
        return DaggerAppComponent.factory().create(this)
            .also { appComponent = it }
    }

    fun getAppDatabase() = appComponent.appDatabase()
}