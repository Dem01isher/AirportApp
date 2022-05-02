package com.leskov.airport.presentation.list_of_items.aircompany.update

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.R
import com.leskov.airport.base.extensions.eventObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.SwipeToDeleteCallback
import com.leskov.airport.databinding.FragmentUpdateListOfCompaniesBinding
import com.leskov.airport.domain.entity.AirCompanyEntity
import com.leskov.airport.presentation.list_of_items.aircompany.ListOfAirCompaniesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfCompaniesFragment :
    BaseVMFragment<ListOfAirCompaniesViewModel, FragmentUpdateListOfCompaniesBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_companies

    override val viewModel: ListOfAirCompaniesViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        binding.btnConfirm.title.setText(R.string.update)

        viewModel.findItemByKey(arguments?.getString("key") ?: "")

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            viewModel.updateItem(
                item = AirCompanyEntity(
                    arguments?.getString("key") ?: "",
                    binding.officeLocation.text.toString(),
                    binding.typeOf.text.toString(),
                    binding.contactNumber.text.toString(),
                    0,
                    binding.dateOfFoundation.text.toString()
                )
            )
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.airCompany.eventObserve(viewLifecycleOwner) {
            binding.airCompany = it
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.airCompany.removeObservers(viewLifecycleOwner)
    }
}