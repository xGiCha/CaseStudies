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
    caseStudyDatabase: CaseStudyDatabase
) {

    val remote = remoteDataSource
    val local = caseStudyDatabase

    fun getCaseStudies() = networkBoundResource(
            query = {
                local.caseStudyDao().readCaseStudies()
            },
            fetch = {
                delay(2000)
                remote.getCaseStudies()
            },
            saveFetchResult = { caseStudies ->
                local.withTransaction {
                    val caseStudyEntity = CaseStudyEntity(caseStudies)
                    local.caseStudyDao().deleteAllCaseStudies()
                    local.caseStudyDao().insertCaseStudy(caseStudyEntity)
                }
            }
    )
}