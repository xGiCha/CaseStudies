package gr.repo.kincart.data

import gr.repo.kincart.database.CaseStudyEntity
import gr.repo.kincart.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CaseStudyInterface {

    fun getCaseStudies() : Flow<Resource<List<CaseStudyEntity>>>
}