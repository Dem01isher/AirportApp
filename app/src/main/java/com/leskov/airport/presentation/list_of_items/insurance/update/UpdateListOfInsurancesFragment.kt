package com.leskov.airport.presentation.list_of_items.insurance.update

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.nonNullObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateListOfInsurancesBinding
import com.leskov.airport.domain.entity.InsuranceEntity
import com.leskov.airport.presentation.list_of_items.insurance.ListOfInsurancesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfInsurancesFragment :
    BaseVMFragment<ListOfInsurancesViewModel, FragmentUpdateListOfInsurancesBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_insurances

    override val viewModel: ListOfInsurancesViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        viewModel.findItemByKey(arguments?.getString("key") ?: "")

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.btnConfirm.title.setText(R.string.update)
        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            viewModel.updateItem(
                item = InsuranceEntity(
                    typeOf = binding.nameOf.text.toString(),
                    serviceName = arguments?.getString("key") ?: "",
                    price = binding.typeOf.text.toString().toInt(),
                    term = binding.contactNumber.text.toString(),
                    formOfInsurance = binding.dateOfFoundation.text.toString()
                )
            )
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.insurance.nonNullObserve(viewLifecycleOwner) {
            binding.apply {
                nameOf.setText(it.typeOf)
                officeLocation.setText(it.serviceName)
                typeOf.setText(it.price.toString())
                contactNumber.setText(it.term)
                dateOfFoundation.setText(it.formOfInsurance)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.insurance.removeObservers(viewLifecycleOwner)
    }
}