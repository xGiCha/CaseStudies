package gr.repo.kincart.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
        entities = [CaseStudyEntity::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(MoviesTypeConverter::class)
abstract class CaseStudyDatabase : RoomDatabase() {

    abstract fun caseStudyDao(): CaseStudyDao

}