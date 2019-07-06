package ca.cbc.testingfun2.data

import kotlinx.coroutines.delay

object FakeGitHubJobsService : GitHubJobsService {

    override suspend fun fetchJobs(): List<GitHubJob> {
        // For demonstration purposes only. Don't include this in a real project.
        delay(2000)
        return mutableListOf<GitHubJob>().apply {
            repeat(5) {
                add(TheBestJobEverProvider.get())
            }
        }
    }
}