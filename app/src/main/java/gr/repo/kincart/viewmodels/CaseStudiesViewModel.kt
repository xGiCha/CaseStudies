package gr.imdb.movies.viewmodels

import android.app.Application
import android.provider.MediaStore
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import gr.repo.kincart.data.CaseStudyInterface
import gr.repo.kincart.data.CaseStudyRepository
import gr.repo.kincart.database.CaseStudyEntity
import gr.repo.kincart.models.CaseStudy
import gr.repo.kincart.models.Studies
import gr.repo.kincart.utils.Resource
import gr.repo.kincart.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CaseStudiesViewModel @ViewModelInject constructor(
    private val repository: CaseStudyRepository
) : ViewModel() {

    val caseStudies = repository.getCaseStudies().asLiveData()

}