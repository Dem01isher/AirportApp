package com.leskov.airport.presentation.list_of_items

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.*
import com.leskov.airport.databinding.FragmentListOfItemsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfItemsFragment : BaseVMFragment<ListOfItemsViewModel, FragmentListOfItemsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_list_of_items

    override val viewModel: ListOfItemsViewModel by viewModels()

    private val adapter = ListOfItemsAdapter()

    override fun initListeners() {
        super.initListeners()

        binding.rvListOfItems.adapter = adapter

        binding.flAddItem.setOnClickWithDebounce {
            navController.navigate(R.id.action_listOfItemsFragment_to_insertNewItemFragment)
        }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.listOfData.nonNullObserve(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loading.nonNullObserve(viewLifecycleOwner) {
            binding.refreshLayout.updateVisibility(it)
        }

        viewModel.errorMessage.nonNullObserve(viewLifecycleOwner){
            showMessage(it, Toast.LENGTH_SHORT)
        }
    }
}