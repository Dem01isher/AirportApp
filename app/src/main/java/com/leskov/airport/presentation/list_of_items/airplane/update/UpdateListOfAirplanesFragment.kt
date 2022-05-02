package com.leskov.airport.presentation.list_of_items.airplane.update

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.eventObserve
import com.leskov.airport.base.extensions.isInputsFilled
import com.leskov.airport.base.extensions.nonNullObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateListOfAirplanesBinding
import com.leskov.airport.domain.entity.AirplaneEntity
import com.leskov.airport.presentation.list_of_items.airplane.ListOfAirplanesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfAirplanesFragment :
    BaseVMFragment<ListOfAirplanesViewModel, FragmentUpdateListOfAirplanesBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_airplanes

    override val viewModel: ListOfAirplanesViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        viewModel.findItemByKey(arguments?.getString("key") ?: "")

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.btnConfirm.title.setText(R.string.update)

        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            if (!isInputsFilled(binding.nameOf, binding.officeLocation, binding.typeOf, binding.contactNumber, binding.dateOfFoundation)){
                return@setOnClickWithDebounce
            } else {
                viewModel.updateItem(
                    item = AirplaneEntity(
                        number = binding.nameOf.text.toString().toInt(),
                        type = binding.officeLocation.text.toString(),
                        model = arguments?.getString("key") ?: "",
                        loadCapacity = binding.contactNumber.text.toString(),
                        producer = binding.dateOfFoundation.text.toString()
                    )
                )
                navController.popBackStack()
            }
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.airplane.nonNullObserve(viewLifecycleOwner){
            binding.apply {
                nameOf.setText(it.number.toString())
                officeLocation.setText(it.type)
                contactNumber.setText(it.loadCapacity)
                dateOfFoundation.setText(it.producer)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.airplane.removeObservers(viewLifecycleOwner)
    }
}