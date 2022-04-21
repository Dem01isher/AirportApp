package com.leskov.airport.presentation.insert_new_item

import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.domain.use_case.InsertItemByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertNewItemViewModel @Inject constructor(private val insertItemByTypeUseCase: InsertItemByTypeUseCase) :
    BaseViewModel() {

    fun setType(type: String){
        if (type.isEmpty()) return
        insertItemByTypeUseCase.setType(type)
    }

    fun insertNewItem(item: Any?) {
        viewModelScope.launch(dispatcherController.launchInMain()) {
            insertItemByTypeUseCase.insertItemByType(item)
        }
    }
}
