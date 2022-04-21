package com.leskov.airport.presentation.list_of_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.domain.use_case.RemoveItemByTypeUseCase
import com.leskov.airport.domain.use_case.SearchDataByTypeUseCase
import com.leskov.airport.domain.use_case.SelectListTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfItemsViewModel @Inject constructor(
    private val selectListTypeUseCase: SelectListTypeUseCase,
    private val searchDataByTypeUseCase: SearchDataByTypeUseCase,
    private val removeItemByTypeUseCase: RemoveItemByTypeUseCase
) :
    BaseViewModel() {

    private val _listOfData =
        SingleEventLiveData<Event<List<Any?>>>()
    val listOfData: LiveData<Event<List<Any?>>> = _listOfData

    init {
        fetchSelectedTypeData()
    }

    fun setType(type: String) {
        if (type.isEmpty()) return
        selectListTypeUseCase.setType(type)
        searchDataByTypeUseCase.setType(type)
        removeItemByTypeUseCase.setType(type)
    }

    fun fetchSelectedTypeData() {
        _loading.postEvent(true)
        _listOfData.postEvent(selectListTypeUseCase.fetchSelectedTypeList())
        _loading.postEvent(false)
    }

    fun searchDataByType(searchQuery: String){
        viewModelScope.launch {
            _loading.postEvent(true)
            delay(1500)
            _listOfData.postEvent(searchDataByTypeUseCase.searchSelectedTypeData(searchQuery))
            delay(1500)
            _loading.postEvent(false)
        }
    }

    fun removeItemByType(item: Any?){
        viewModelScope.launch(dispatcherController.launchInMain()) {
            _loading.postEvent(true)
            removeItemByTypeUseCase.removeSelectedTypeItem(item)
            _loading.postEvent(false)
        }
    }

}