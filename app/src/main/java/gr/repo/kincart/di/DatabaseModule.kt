package gr.repo.kincart.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.repo.kincart.database.CaseStudyDatabase
import gr.repo.kincart.utils.Constants.Companion.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            CaseStudyDatabase::class.java,
            DATABASE_NAME
    ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideDao(database: CaseStudyDatabase) = database.caseStudyDao()

}