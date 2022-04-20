package com.leskov.airport.presentation.list_of_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.AirportRepository
import com.leskov.airport.domain.entity.AirportEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfItemsViewModel @Inject constructor(private val repository: AirportRepository) : BaseViewModel() {

    private val _listOfData =
        SingleEventLiveData<List<AirportEntity>>()
    val listOfData : LiveData<List<AirportEntity>> = _listOfData

    val action = SingleEventLiveData<Event<Unit>>()

    init {
        fetchAllData()
    }

    fun fetchAllData(){
        viewModelScope.launch {
            _loading.postEvent(true)
            _listOfData.postValue(repository.fetchAllData())
            _loading.postEvent(false)
        }
    }

    suspend fun searchData(searchText: String) {
        _listOfData.postValue(repository.searchData(searchText))
    }

    fun removeData(item: AirportEntity){
        viewModelScope.launch(dispatcherController.launchInMain()) {
            _loading.postEvent(true)
            repository.deleteItem(item)
            _loading.postEvent(false)
            action.postEvent(Unit)
        }
    }
}