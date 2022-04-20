package com.leskov.airport.presentation.list_of_items

import com.leskov.airport.R
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemAirportBinding
import com.leskov.airport.domain.entity.AirportEntity

class ListOfItemsAdapter : BaseListAdapter<AirportEntity, ListItemAirportBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_airport

    private var isVisible = true

    override fun onViewHolderCreated(binding: ListItemAirportBinding, position: Int) {
        binding.apply {

            item = currentList[position]

            cardContainer.setOnClickWithDebounce {
                if (isVisible) {
                    descriptionLayout.updateVisibility(true)
                    arrow.rotation = 360f
                    isVisible = false
                } else {
                    descriptionLayout.updateVisibility(false)
                    arrow.rotation = 270f
                    isVisible = true
                }
            }
        }
    }

}