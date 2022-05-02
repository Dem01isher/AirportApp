package com.leskov.airport.presentation.list_of_items.route

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.RouteRepository
import com.leskov.airport.domain.entity.RouteEntity
import com.leskov.airport.domain.entity.TypeOfEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfRoutesViewModel @Inject constructor(
    private val repository: RouteRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _route = SingleEventLiveData<Event<RouteEntity>>()
    val route: LiveData<Event<RouteEntity>> = _route

    fun updateItem(item: RouteEntity){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: String){
        _route.postEvent(repository.findItemByKey(key))
    }

    fun searchData(searchQuery: String) : LiveData<List<RouteEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: RouteEntity){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}