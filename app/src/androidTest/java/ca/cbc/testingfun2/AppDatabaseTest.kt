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

// These tests are a bit pointless, since effectively they're just testing the Room framework. But
// they are useful in outlining how to set up tests for a Room database that uses coroutines. If
// the GitHubJobsDao had @Transaction methods with complex logic, I'd then want it to be tested
// in a class like this one.
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    lateinit var database: AppDatabase
    lateinit var dao: GitHubJobsDao
    private val job = GitHubJob(
        id = "1",
        title = "a",
        company = "b"
    )

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
        dao.insert(job)

        val gitHubJobs = dao.queryAll()

        assert(gitHubJobs.size == 1)
        assert(gitHubJobs.first() == job)
    }

    @Test
    fun deleteAll() = runBlocking {
        dao.insert(job)
        dao.deleteAll()

        val gitHubJobs = dao.queryAll()

        assert(gitHubJobs.isEmpty())
    }
}
