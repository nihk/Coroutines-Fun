package ca.cbc.testingfun2.di

import androidx.lifecycle.ViewModelProvider
import ca.cbc.testingfun2.vm.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}