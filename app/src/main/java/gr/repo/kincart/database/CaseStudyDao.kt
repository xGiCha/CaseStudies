package gr.repo.kincart.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CaseStudyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCaseStudy(caseStudyEntity: CaseStudyEntity)

    @Query("SELECT * FROM case_study_table")
    fun readCaseStudies(): Flow<List<CaseStudyEntity>>

    @Delete
    suspend fun deleteCaseStudy(caseStudyEntity: CaseStudyEntity)

    @Query("DELETE FROM case_study_table")
    suspend fun deleteAllCaseStudies()

}