package com.leskov.airport.presentation.list_of_items.race

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.RaceRepository
import com.leskov.airport.domain.entity.RaceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfRacesViewModel @Inject constructor(
    private val repository: RaceRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _race = SingleEventLiveData<Event<RaceEntity>>()
    val race: LiveData<Event<RaceEntity>> = _race

    fun updateItem(item: RaceEntity){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: String){
        _race.postEvent(repository.findItemByKey(key))
    }

    fun searchData(searchQuery: String) : LiveData<List<RaceEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: RaceEntity){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}