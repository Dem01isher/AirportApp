package com.leskov.airport.presentation.list_of_items.route.update

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.eventObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateListOfRoutesBinding
import com.leskov.airport.domain.entity.RouteEntity
import com.leskov.airport.presentation.list_of_items.route.ListOfRoutesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfRoutesFragment :
    BaseVMFragment<ListOfRoutesViewModel, FragmentUpdateListOfRoutesBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_routes

    override val viewModel: ListOfRoutesViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        viewModel.findItemByKey(arguments?.getString("key") ?: "")

        binding.btnConfirm.title.setText(R.string.update)
        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            viewModel.updateItem(
                item = RouteEntity(
                    numberOf = binding.nameOf.text.toString().toInt(),
                    status = binding.officeLocation.text.toString(),
                    departureCountry = arguments?.getString("key") ?: "",
                    destinationCountry = binding.contactNumber.text.toString(),
                    length = binding.dateOfFoundation.text.toString()
                )
            )
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.route.eventObserve(viewLifecycleOwner) {
            binding.route = it
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.route.removeObservers(viewLifecycleOwner)
    }
}