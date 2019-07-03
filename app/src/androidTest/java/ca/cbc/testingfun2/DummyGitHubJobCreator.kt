package ca.cbc.testingfun2

import ca.cbc.testingfun2.data.GitHubJob

object DummyGitHubJobCreator {

    @JvmStatic
    fun one() = GitHubJob(
        id = "1234",
        title = "title",
        company = "company"
    )
}