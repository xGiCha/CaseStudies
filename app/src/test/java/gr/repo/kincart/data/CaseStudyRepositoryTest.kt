package gr.repo.kincart.data

import android.app.Application
import android.app.ApplicationErrorReport
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import gr.imdb.movies.viewmodels.CaseStudiesViewModel
import gr.repo.kincart.database.CaseStudyDatabase
import gr.repo.kincart.database.CaseStudyEntity
import gr.repo.kincart.models.CaseStudy
import gr.repo.kincart.models.Studies
import gr.repo.kincart.utils.Resource
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CaseStudyRepositoryTest {

    @Mock
    lateinit var repository: CaseStudyRepository

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var caseStudyDatabase: CaseStudyDatabase

    @Mock
    lateinit var caseStudiesApi: CaseStudiesApi

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CaseStudiesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSource(caseStudiesApi)
        repository = CaseStudyRepository(remoteDataSource, caseStudyDatabase)
        viewModel = CaseStudiesViewModel(repository)
    }

    // create object for testing
    val caseStudy1 = CaseStudy(id = 10, teaser = "hello world")
    val caseStudy2 = CaseStudy(id = 20, teaser = "welcome")
    val list = mutableListOf(caseStudy1, caseStudy2).toList()
    val studies = Studies(list)
    val list1 = mutableListOf(studies)
    val resource = Resource(list1)
    val flowObject = flowOf(resource)

    @Test
    fun getCaseStudies() {
        repository = Mockito.mock(CaseStudyRepository::class.java)
        Mockito.doReturn(flowObject).`when`(repository).getCaseStudies()
        assertEquals(flowObject, repository.getCaseStudies())
    }
}