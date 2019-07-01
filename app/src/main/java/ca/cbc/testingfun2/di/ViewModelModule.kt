package ca.cbc.testingfun2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.cbc.testingfun2.vm.GitHubJobsViewModel
import ca.cbc.testingfun2.vm.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GitHubJobsViewModel::class)
    abstract fun gitHubJobsViewModel(gitHubJobsViewModel: GitHubJobsViewModel): ViewModel
}