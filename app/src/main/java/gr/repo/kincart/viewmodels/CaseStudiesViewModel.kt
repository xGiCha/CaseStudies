package gr.imdb.movies.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import gr.repo.kincart.data.CaseStudyRepository
import gr.repo.kincart.models.Studies
import gr.repo.kincart.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CaseStudiesViewModel @ViewModelInject constructor(
        private val repository: CaseStudyRepository
) : ViewModel() {

    //    val caseStudies = repository.getCaseStudies().asLiveData()
    var caseStudies: MutableLiveData<Resource<Studies>> = MutableLiveData<Resource<Studies>>()
    var testCaseStudiesError: MutableLiveData<Resource<Studies>> = MutableLiveData<Resource<Studies>>()

    init {
        getCaseStudies()
    }

    fun getCaseStudies() {
        caseStudies.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            runCatching {
                repository.remote.getCaseStudies()
            }.onFailure {
//                it.printStackTrace()
                testCaseStudiesError.postValue(Resource.Error(Throwable(message = "something went wrong")))
            }.onSuccess {
                try {
                    caseStudies.postValue(Resource.Success(it))
                } catch (e: Exception) {
                    caseStudies.postValue(Resource.Error(Throwable(message = "something went wrong")))
                }
            }
        }
    }
}