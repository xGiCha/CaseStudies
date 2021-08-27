package gr.repo.kincart.data

import gr.repo.kincart.database.CaseStudyDao
import gr.repo.kincart.database.CaseStudyEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val caseStudyDao: CaseStudyDao
) {

    fun readCaseStudies(): Flow<List<CaseStudyEntity>> {
        return caseStudyDao.readCaseStudies()
    }

    suspend fun insertCaseStudy(caseStudyEntity: CaseStudyEntity) {
        caseStudyDao.insertCaseStudy(caseStudyEntity)
    }

    suspend fun deleteCaseStudy(caseStudyEntity: CaseStudyEntity) {
        caseStudyDao.deleteCaseStudy(caseStudyEntity)
    }
    suspend fun deleteAllCaseStudies() {
        caseStudyDao.deleteAllCaseStudies()
    }

    fun dao(): CaseStudyDao = caseStudyDao

}