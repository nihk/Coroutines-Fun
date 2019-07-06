package ca.cbc.testingfun2.di

import ca.cbc.testingfun2.data.FakeGitHubJobsService
import ca.cbc.testingfun2.data.GitHubJobsService
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object NetworkingModule {

    @Reusable
    @Provides
    @JvmStatic
    fun gitHubJobsService(
    ): GitHubJobsService = FakeGitHubJobsService
}