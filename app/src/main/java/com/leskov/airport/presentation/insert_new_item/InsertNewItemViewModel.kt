package com.leskov.airport.presentation.insert_new_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.AirportRepository
import com.leskov.airport.domain.AirportEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertNewItemViewModel @Inject constructor(private val repository: AirportRepository): BaseViewModel() {

    private val _newAction = MutableLiveData<Unit>()
    val newAction: LiveData<Unit> = _newAction

    fun insertNewItem(item: AirportEntity){
        viewModelScope.launch {
            launchSingleRequest(_newAction){
                repository.insertItem(item)
                postValue(Unit)
            }
        }
    }
}