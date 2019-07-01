package ca.cbc.testingfun2.di

import androidx.lifecycle.ViewModel
import ca.cbc.testingfun2.vm.GitHubJobsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelFactoryModule::class
    ]
)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GitHubJobsViewModel::class)
    abstract fun gitHubJobsViewModel(gitHubJobsViewModel: GitHubJobsViewModel): ViewModel
}