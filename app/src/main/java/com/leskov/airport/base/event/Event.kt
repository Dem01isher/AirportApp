package com.leskov.airport.base.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    var handled = false
        private set

    /**
     * Returns the content and prevents its use again.
     */
    fun getContent(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }

    fun unsafeGetContent() = content
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onUnhandledEvent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onUnhandledEvent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContent()?.let {
            onUnhandledEvent.invoke(it)
        }
    }
}

fun <T> MutableLiveData<Event<T>>.postEvent(value: T) {
    postValue(Event(value))
}

fun <T> MutableLiveData<Event<T>>.setEvent(value: T) {
    setValue(Event(value))
}

inline fun <reified T> MutableLiveData<Event<T>>.postActiveEvent(value: T) {
    if (hasActiveObservers()) {
        postValue(Event(value))
    } else {
        Timber.i("No active observers for: ${T::class.simpleName}")
    }
}