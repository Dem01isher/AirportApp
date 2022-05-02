package com.leskov.airport.presentation.list_of_items.airport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class ListOfAirportsViewModel @Inject constructor(
    private val repository: AirportRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _airport = MutableLiveData<AirportEntity>()
    val airport: LiveData<AirportEntity> = _airport

    fun updateItem(item: AirportEntity){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: String){
        _airport.postValue(repository.findItemByKey(key))
    }

    fun searchData(searchQuery: String) : LiveData<List<AirportEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: AirportEntity){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}