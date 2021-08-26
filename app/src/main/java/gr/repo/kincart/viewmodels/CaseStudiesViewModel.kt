package gr.imdb.movies.viewmodels

import android.app.Application
import android.provider.MediaStore
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import gr.repo.kincart.data.CaseStudyRepository
import gr.repo.kincart.models.CaseStudy
import gr.repo.kincart.models.Studies
import gr.repo.kincart.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CaseStudiesViewModel @ViewModelInject constructor(
    private val repository: CaseStudyRepository,
    application: Application
) : AndroidViewModel(application) {

    /** RETROFIT */
    var caseStudy: SingleLiveEvent<Studies> = SingleLiveEvent()

    fun getCaseStudies(){
        viewModelScope.launch(Dispatchers.Default) {
            runCatching {
                repository.remote.getCaseStudies()
            }.onFailure {
                it.printStackTrace()
            }.onSuccess {response ->
                if(response.isSuccessful){
                    response.body()?.let {
                        caseStudy.postValue(it)
                    }
                }
            }
        }
    }


}