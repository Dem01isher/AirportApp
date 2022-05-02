package com.leskov.airport.base.list_adapter

interface ListController<T> {

    fun addItem(item: T)

    fun removeItem(item: T)

    fun clear()
}