package com.leskov.airport.presentation.list_of_items.airport.update

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.nonNullObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateListOfAirportsBinding
import com.leskov.airport.domain.entity.AirportEntity
import com.leskov.airport.presentation.list_of_items.airport.ListOfAirportsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfAirportsFragment :
    BaseVMFragment<ListOfAirportsViewModel, FragmentUpdateListOfAirportsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_airports

    override val viewModel: ListOfAirportsViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        viewModel.findItemByKey(arguments?.getString("key") ?: "")

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.btnConfirm.title.setText(R.string.update)
        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            viewModel.updateItem(
                item = AirportEntity(
                    city = arguments?.getString("key") ?: "",
                    title = binding.nameOf.text.toString(),
                    countOfTerminals = binding.officeLocation.text.toString().toInt(),
                    countryLocation = binding.typeOf.text.toString(),
                    numberOfLanes = binding.dateOfFoundation.text.toString().toInt()
                )
            )
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.airport.nonNullObserve(viewLifecycleOwner) {
            binding.apply {
                nameOf.setText(it.title)
                officeLocation.setText(it.countOfTerminals.toString())
                typeOf.setText(it.countryLocation)
                contactNumber.setText(it.city)
                dateOfFoundation.setText(it.numberOfLanes.toString())
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.airport.removeObservers(viewLifecycleOwner)
    }
}