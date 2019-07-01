package ca.cbc.testingfun2.data

import javax.inject.Inject

class GitHubJobsRepository @Inject constructor(
    val gitHubJobsService: GitHubJobsService,
    val gitHubJobDao: GitHubJobDao
) {


}