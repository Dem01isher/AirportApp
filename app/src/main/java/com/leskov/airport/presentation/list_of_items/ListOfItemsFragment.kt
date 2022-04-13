package com.leskov.airport.presentation.list_of_items

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.hideRefresh
import com.leskov.airport.base.utils.showMessage
import com.leskov.airport.base.utils.showRefresh
import com.leskov.airport.base.utils.updateVisibility
import com.leskov.airport.databinding.FragmentListOfItemsBinding

class ListOfItemsFragment : BaseVMFragment<ListOfItemsViewModel, FragmentListOfItemsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_list_of_items

    override val viewModel: ListOfItemsViewModel by viewModels()

    private val adapter = ListOfItemsAdapter()

    override fun initListeners() {
        super.initListeners()

        binding.rvListOfItems.adapter = adapter
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.listOfData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.refreshLayout.updateVisibility(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){
            showMessage(it, Toast.LENGTH_SHORT)
        }
    }
}