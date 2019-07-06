package ca.cbc.testingfun2

import ca.cbc.testingfun2.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.factory().create(this)
}