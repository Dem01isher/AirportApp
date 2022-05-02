package com.leskov.airport.presentation.list_of_items.airport

import com.leskov.airport.R
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemAirportBinding
import com.leskov.airport.domain.entity.AirportEntity

class ListOfAirportsAdapter : BaseListAdapter<AirportEntity, ListItemAirportBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_airport

    private var onClickListener: ((AirportEntity) -> Unit)? = null
    private var isVisible: Boolean = true

    override fun onViewHolderCreated(binding: ListItemAirportBinding, position: Int) {
        binding.apply {
            airport = currentList[position]
            headerLayout.setOnClickListener {
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
            cardContainer.setOnClickListener {
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
        binding.cardContainer.setOnLongClickListener {
            onClickListener?.invoke(currentList[position])
            true
        }
    }

    fun setOnItemClickListener(listener:(AirportEntity) -> Unit){
        onClickListener = listener
    }
}