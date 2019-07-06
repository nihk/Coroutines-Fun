package ca.cbc.testingfun2.data

object TheBestJobEverProvider {
    private var id = 1
    const val TITLE = "Senior Developer"

    @JvmStatic
    fun get(): GitHubJob {
        return GitHubJob(
            id = id++.toString(),
            title = TITLE,
            company = "CBC"
        )
    }
}