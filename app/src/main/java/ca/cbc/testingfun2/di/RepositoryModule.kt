package ca.cbc.testingfun2.di

import ca.cbc.testingfun2.data.GitHubJobsRepository
import ca.cbc.testingfun2.data.GitHubJobsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class RepositoryModule {

    @Reusable
    @Binds
    abstract fun gitHubJobsRepository(
        repositoryImpl: GitHubJobsRepositoryImpl
    ): GitHubJobsRepository
}