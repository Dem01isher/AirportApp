package com.leskov.airport.presentation.list_of_items.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.TeamRepository
import com.leskov.airport.domain.entity.TeamEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfTeamsViewModel @Inject constructor(
    private val repository: TeamRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _team = SingleEventLiveData<Event<TeamEntity>>()
    val team: LiveData<Event<TeamEntity>> = _team

    fun updateItem(item: TeamEntity) {
        if (item == null) return
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: Int){
        if (key == 0) return
        _team.postEvent(repository.findItemByKey(key))
    }

    fun searchData(searchQuery: String) : LiveData<List<TeamEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: TeamEntity){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}