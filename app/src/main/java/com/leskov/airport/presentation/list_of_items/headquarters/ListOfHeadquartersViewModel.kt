package com.leskov.airport.presentation.list_of_items.headquarters

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.HeadQuarterRepository
import com.leskov.airport.domain.entity.HeadQuarterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfHeadquartersViewModel @Inject constructor(
    private val repository: HeadQuarterRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _headQuarter = SingleEventLiveData<Event<HeadQuarterEntity>>()
    val headQuarter: LiveData<Event<HeadQuarterEntity>> = _headQuarter

    fun updateItem(item: HeadQuarterEntity){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: Int){
        _headQuarter.postEvent(repository.findItemByType(key))
    }

    fun searchData(searchQuery: String) : LiveData<List<HeadQuarterEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: HeadQuarterEntity){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}