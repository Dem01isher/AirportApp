package com.leskov.airport.presentation.list_of_items.aircompany

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.AirCompanyRepository
import com.leskov.airport.domain.entity.AirCompanyEntity
import com.leskov.airport.domain.entity.AirportEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfAirCompaniesViewModel @Inject constructor(
    private val repository: AirCompanyRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _airCompany = SingleEventLiveData<Event<AirCompanyEntity>>()
    val airCompany: LiveData<Event<AirCompanyEntity>> = _airCompany

    fun updateItem(item: AirCompanyEntity){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: String){
        _airCompany.postEvent(repository.findItemByKey(key))
    }

    fun searchData(searchQuery: String) : LiveData<List<AirCompanyEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: AirCompanyEntity) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}