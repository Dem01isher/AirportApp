package com.leskov.airport.presentation.list_of_items

import com.leskov.airport.R
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemAirportBinding
import com.leskov.airport.domain.AirportEntity

class ListOfItemsAdapter : BaseListAdapter<AirportEntity, ListItemAirportBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_airport

    override fun initListeners(binding: ListItemAirportBinding, item: AirportEntity) {
        TODO("Not yet implemented")
    }
}