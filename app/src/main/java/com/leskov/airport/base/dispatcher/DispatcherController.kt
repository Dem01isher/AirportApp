package com.leskov.airport.base.dispatcher

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface DispatcherController {

    fun launchInDefault() : CoroutineContext

    fun launchInMain() : CoroutineContext

    fun launchInInputOutput() : CoroutineContext

    fun launchInUnUnconfined() : CoroutineContext

    class Base : DispatcherController {

        override fun launchInDefault(): CoroutineContext = Dispatchers.Default

        override fun launchInMain(): CoroutineContext = Dispatchers.Main

        override fun launchInInputOutput(): CoroutineContext = Dispatchers.IO

        override fun launchInUnUnconfined(): CoroutineContext = Dispatchers.Unconfined

    }
}