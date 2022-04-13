package com.leskov.airport.base

interface ListController<T> {

    fun addItem(item: T)

    fun submitList(list: List<T>)

    fun removeItem(item: T)

    fun clear()
}