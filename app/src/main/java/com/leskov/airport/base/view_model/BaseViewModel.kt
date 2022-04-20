package com.leskov.airport.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.dispatcher.DispatcherController
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val _loading = SingleEventLiveData<Event<Boolean>>()
    val loading: LiveData<Event<Boolean>> = _loading

    private val _errorMessage = SingleEventLiveData<Event<String>>()
    val errorMessage: SingleEventLiveData<Event<String>> = _errorMessage

    protected val dispatcherController: DispatcherController = DispatcherController.Base()

    protected suspend fun <T> launchSingleRequest(
        liveData: MutableLiveData<T>,
        context: CoroutineScope = viewModelScope,
        block: suspend (MutableLiveData<T>.() -> Unit)
    ) = context.runCatching {
        _loading.postEvent(true)
        block.invoke(liveData)
    }.onSuccess {
        _loading.postEvent(false)
    }.onFailure {
        _loading.postEvent(false)
        _errorMessage.postEvent(it.localizedMessage)
    }
}