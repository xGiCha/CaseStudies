package gr.repo.kincart.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import gr.imdb.movies.viewmodels.CaseStudiesViewModel
import gr.repo.kincart.TestCoroutineRule
import gr.repo.kincart.data.CaseStudiesApi
import gr.repo.kincart.data.CaseStudyRepository
import gr.repo.kincart.data.RemoteDataSource
import gr.repo.kincart.database.CaseStudyDatabase
import gr.repo.kincart.models.Studies
import gr.repo.kincart.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.lang.reflect.TypeVariable

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CaseStudiesViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: CaseStudyRepository

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var caseStudyDatabase: CaseStudyDatabase

    @Mock
    lateinit var caseStudiesApi: CaseStudiesApi

    @Mock
    private lateinit var apiCaseStudyObserver: Observer<Resource<Studies>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSource(caseStudiesApi)
        repository = CaseStudyRepository(remoteDataSource, caseStudyDatabase)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(Studies())
                    .`when`(caseStudiesApi)
                    .getCaseStudies()
            val viewModel = CaseStudiesViewModel(repository)
            viewModel.caseStudies.observeForever(apiCaseStudyObserver)
            verify(caseStudiesApi).getCaseStudies()
            verify(apiCaseStudyObserver).onChanged(viewModel.caseStudies.value)
            viewModel.caseStudies.removeObserver(apiCaseStudyObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "something went wrong"
            doThrow(RuntimeException(errorMessage))
                    .`when`(caseStudiesApi)
                    .getCaseStudies()
            val viewModel = CaseStudiesViewModel(repository)
            viewModel.testCaseStudiesError.observeForever(apiCaseStudyObserver)
            verify(caseStudiesApi).getCaseStudies()
            verify(apiCaseStudyObserver).onChanged(viewModel.testCaseStudiesError.value)
            viewModel.testCaseStudiesError.removeObserver(apiCaseStudyObserver)
        }
    }
}