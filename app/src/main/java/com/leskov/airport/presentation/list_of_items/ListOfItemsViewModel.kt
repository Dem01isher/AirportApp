package com.leskov.airport.presentation.list_of_items

import androidx.lifecycle.LiveData
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.domain.use_case.SelectListTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListOfItemsViewModel @Inject constructor(private val selectListTypeUseCase: SelectListTypeUseCase) :
    BaseViewModel() {

    private val _listOfData =
        SingleEventLiveData<Event<List<Any?>>>()
    val listOfData: LiveData<Event<List<Any?>>> = _listOfData

    init {
        fetchSelectedTypeData()
    }

    fun setType(type: String){
        selectListTypeUseCase.setType(type)
    }

    fun fetchSelectedTypeData() {
        _listOfData.postEvent(selectListTypeUseCase.fetchSelectedTypeList())
    }

}