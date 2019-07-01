package ca.cbc.testingfun2.data

import retrofit2.http.GET

interface GitHubJobsService {

    companion object {
        const val baseUrl = "https://jobs.github.com/"
    }

    @GET("positions.json")
    suspend fun fetchJobs(): List<GitHubJob>
}