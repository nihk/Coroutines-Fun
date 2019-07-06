package ca.cbc.testingfun2.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.cbc.testingfun2.MainCoroutineRule
import ca.cbc.testingfun2.data.GitHubJob
import ca.cbc.testingfun2.data.GitHubJobsRepository
import ca.cbc.testingfun2.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(AndroidJUnit4::class)
class GitHubJobsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    lateinit var repository: GitHubJobsRepository
    lateinit var viewModel: GitHubJobsViewModel

    private val dummyJob = GitHubJob(
        id = "1",
        title = "a",
        company = "b"
    )

    @Before
    fun setUp() {
        repository = mock(GitHubJobsRepository::class.java)
        viewModel = GitHubJobsViewModel(repository)
    }

    @Test
    fun viewModelJobsLiveDataGetsValueFromRepository() {
        val expected = Resource.Success(listOf(dummyJob))
        val liveData = MutableLiveData<Resource<List<GitHubJob>>>().apply {
            value = expected
        }
        `when`(repository.getGitHubJobs()).thenReturn(liveData)

        assert(viewModel.gitHubJobs.value == null)
        viewModel.fetchGitHubJobs()
        assert(viewModel.gitHubJobs.value == expected)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertGitHubJobCallsRepository() = mainCoroutineRule.runBlockingTest {
        viewModel.insertJob(dummyJob)
        verify(repository).insertJob(dummyJob)
    }
}