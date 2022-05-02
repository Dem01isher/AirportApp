package com.leskov.airport.presentation.list_of_items.aircompany

import com.leskov.airport.R
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemAircompanyBinding
import com.leskov.airport.domain.entity.AirCompanyEntity

class ListOfAirCompaniesAdapter : BaseListAdapter<AirCompanyEntity, ListItemAircompanyBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_aircompany

    private var onClickListener: ((AirCompanyEntity) -> Unit)? = null
    private var isVisible: Boolean = true

    override fun onViewHolderCreated(binding: ListItemAircompanyBinding, position: Int) {
        binding.apply {
            airCompany = currentList[position]
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

    fun setOnItemClickListener(listener: (AirCompanyEntity) -> Unit){
        onClickListener = listener
    }
}