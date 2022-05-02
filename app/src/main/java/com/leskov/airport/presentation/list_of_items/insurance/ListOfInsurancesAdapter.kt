package com.leskov.airport.presentation.list_of_items.insurance

import com.leskov.airport.R
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemInsuranceBinding
import com.leskov.airport.domain.entity.InsuranceEntity

class ListOfInsurancesAdapter : BaseListAdapter<InsuranceEntity, ListItemInsuranceBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_insurance

    private var onClickListener: ((InsuranceEntity) -> Unit)? = null
    private var isVisible: Boolean = true

    override fun onViewHolderCreated(binding: ListItemInsuranceBinding, position: Int) {
        val insurance = currentList[position]
        binding.apply {
            title.text = insurance.serviceName
            type.text = insurance.typeOf
            formOfInsurance.text = insurance.formOfInsurance
            loadCapacity.text = insurance.term
            price.text = insurance.price.toString()
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

    fun setOnItemClickListener(listener: (InsuranceEntity) -> Unit){
        onClickListener = listener
    }
}