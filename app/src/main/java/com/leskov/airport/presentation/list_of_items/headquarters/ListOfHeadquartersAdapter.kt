package com.leskov.airport.presentation.list_of_items.headquarters

import com.leskov.airport.R
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemHeadquarterBinding
import com.leskov.airport.domain.entity.HeadQuarterEntity

class ListOfHeadquartersAdapter : BaseListAdapter<HeadQuarterEntity, ListItemHeadquarterBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_headquarter

    private var onClickListener: ((HeadQuarterEntity) -> Unit)? = null
    private var isVisible: Boolean = true

    override fun onViewHolderCreated(binding: ListItemHeadquarterBinding, position: Int) {
        val headquarter = currentList[position]
        binding.apply {
            title.text = headquarter.numberOf.toString()
            type.text = headquarter.numberOfBeds.toString()
            model.text = headquarter.availabilityOfKitchen
            loadCapacity.text = headquarter.countOfLevels.toString()
            entertainmentRoom.text = headquarter.entertainmentRoom
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

    fun setOnItemClickListener(listener:(HeadQuarterEntity) -> Unit){
        onClickListener = listener
    }
}