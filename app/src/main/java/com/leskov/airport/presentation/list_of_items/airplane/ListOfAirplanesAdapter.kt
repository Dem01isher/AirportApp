package com.leskov.airport.presentation.list_of_items.airplane

import com.leskov.airport.R
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemAirplaneBinding
import com.leskov.airport.domain.entity.AirplaneEntity

class ListOfAirplanesAdapter : BaseListAdapter<AirplaneEntity, ListItemAirplaneBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_airplane

    private var onClickListener: ((AirplaneEntity) -> Unit)? = null
    private var isVisible: Boolean = true

    override fun onViewHolderCreated(binding: ListItemAirplaneBinding, position: Int) {
        binding.item = currentList[position]
        binding.apply {
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
            false
        }
    }

    fun setOnItemClickListener(listener: (AirplaneEntity) -> Unit){
        onClickListener = listener
    }
}