package gr.repo.kincart.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.room.withTransaction
import dagger.hilt.android.scopes.ActivityRetainedScoped
import gr.repo.kincart.database.CaseStudyDatabase
import gr.repo.kincart.database.CaseStudyEntity
import gr.repo.kincart.models.Studies
import gr.repo.kincart.utils.networkBoundResource
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class CaseStudyRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    caseStudyDatabase: CaseStudyDatabase,
    localDataSource: LocalDataSource

) {

    val remote = remoteDataSource
    val local = localDataSource
    val caseStudyLocal = caseStudyDatabase

    fun getCaseStudies() = networkBoundResource(
            query = {
                local.readCaseStudies()
            },
            fetch = {
                delay(2000)
                remote.getCaseStudies()
            },
            saveFetchResult = { caseStudies ->
                caseStudyLocal.withTransaction {
                    val caseStudyEntity = CaseStudyEntity(caseStudies)
                    local.deleteAllCaseStudies()
                    local.insertCaseStudy(caseStudyEntity)
                }
            }
    )
}