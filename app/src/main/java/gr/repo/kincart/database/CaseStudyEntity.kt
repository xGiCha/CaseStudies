package gr.repo.kincart.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import gr.repo.kincart.models.CaseStudy
import gr.repo.kincart.models.Studies
import gr.repo.kincart.utils.Constants.Companion.CASE_STUDY_TABLE

@Entity(tableName = CASE_STUDY_TABLE)
class CaseStudyEntity(
        var caseStudy: Studies
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}