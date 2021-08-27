package gr.repo.kincart.data

import gr.repo.kincart.models.Studies
import retrofit2.Response
import retrofit2.http.GET

interface CaseStudiesApi {

    @GET("/theappbusiness/engineering-challenge/main/endpoints/v1/caseStudies.json")
    suspend fun getCaseStudies(): Studies



}