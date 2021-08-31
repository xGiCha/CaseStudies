package gr.imdb.movies.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import gr.repo.kincart.data.CaseStudyRepository

class CaseStudiesViewModel @ViewModelInject constructor(
    private val repository: CaseStudyRepository
) : ViewModel() {

    val caseStudies = repository.getCaseStudies().asLiveData()

}