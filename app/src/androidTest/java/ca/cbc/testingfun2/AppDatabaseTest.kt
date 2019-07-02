package ca.cbc.testingfun2

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.cbc.testingfun2.data.AppDatabase
import ca.cbc.testingfun2.data.GitHubJob
import ca.cbc.testingfun2.data.GitHubJobsDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    lateinit var database: AppDatabase
    lateinit var dao: GitHubJobsDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java)
            .build()
        dao = database.gitHubJobsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertGitHubJobSavesData() = runBlocking {
        val gitHubJob = createDummyGitHubJob()
        dao.insert(gitHubJob)

        val gitHubJobs = dao.queryAll()

        assert(gitHubJobs.size == 1)
        assert(gitHubJobs.first() == gitHubJob)
    }

    @Test
    fun deleteAll() = runBlocking {
        val gitHubJob = createDummyGitHubJob()
        dao.insert(gitHubJob)
        dao.deleteAll()

        val gitHubJobs = dao.queryAll()

        assert(gitHubJobs.isEmpty())
    }

    private fun createDummyGitHubJob() = GitHubJob(
        id = "1234",
        title = "title",
        company = "company"
    )
}
