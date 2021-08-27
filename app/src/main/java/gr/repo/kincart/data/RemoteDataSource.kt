package gr.repo.kincart.data

import gr.repo.kincart.models.Studies
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val caseStudiesApi: CaseStudiesApi
) {

    suspend fun getCaseStudies(): Studies{
        return caseStudiesApi.getCaseStudies()
    }
}