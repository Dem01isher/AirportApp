package com.leskov.airport.presentation.list_of_items.airplane

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.AirplaneRepository
import com.leskov.airport.domain.entity.AirplaneEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfAirplanesViewModel @Inject constructor(
    private val repository: AirplaneRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _airplane = MutableLiveData<AirplaneEntity>()
    val airplane : LiveData<AirplaneEntity> = _airplane

    fun updateItem(item: AirplaneEntity){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: String){
        _airplane.postValue(repository.findItemByKey(key))
    }

    fun searchData(searchQuery: String) : LiveData<List<AirplaneEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: AirplaneEntity){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}