package com.leskov.airport.presentation.insert_new_item

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
class InsertNewItemViewModel @Inject constructor(private val repository: AirportRepository) :
    BaseViewModel() {

    fun insertNewItem(item: AirportEntity) {
        viewModelScope.launch(dispatcherController.launchInMain()) {
            repository.insertItem(item)
        }
    }
}
