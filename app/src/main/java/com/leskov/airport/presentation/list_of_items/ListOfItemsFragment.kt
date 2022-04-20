package com.leskov.airport.presentation.list_of_items

import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.SwipeToDeleteCallback
import com.leskov.airport.databinding.FragmentListOfItemsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfItemsFragment : BaseVMFragment<ListOfItemsViewModel, FragmentListOfItemsBinding>() {

    override val layoutId: Int = R.layout.fragment_list_of_items

    override val viewModel: ListOfItemsViewModel by viewModels()

    private val listOfItemsAdapter = ListOfItemsAdapter()

    override fun initListeners() {
        super.initListeners()

        binding.toolbarTitle.text = arguments?.getString("title") ?: ""

        binding.rvListOfItems.adapter = listOfItemsAdapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = binding.rvListOfItems.adapter as ListOfItemsAdapter

                showSnackbarWithRemove("text") {
                    viewModel.removeData(adapter.currentList[viewHolder.bindingAdapterPosition])
                }

            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(binding.rvListOfItems)

        binding.flAddItem.setOnClickWithDebounce {
            navController.navigate(R.id.action_listOfItemsFragment_to_insertNewItemFragment)
        }

        binding.btnSearch.setOnClickWithDebounce {
            binding.searchField.updateVisibility(true)
            binding.arrowBack.updateVisibility(false)
            binding.btnSearch.updateVisibility(false)
            binding.toolbarTitle.updateVisibility(false)
        }

        binding.root.setOnClickListener {
            binding.searchField.clearFocus()
            hideKeyboard(binding.searchField)
            binding.searchField.updateVisibility(false)
            binding.arrowBack.updateVisibility(true)
            binding.btnSearch.updateVisibility(true)
            binding.toolbarTitle.updateVisibility(true)
        }

        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) searchData(p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) searchData(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.arrowBack.setOnClickWithDebounce {
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.listOfData.nonNullObserve(viewLifecycleOwner) { list ->
            listOfItemsAdapter.submitList(list)
        }

        viewModel.action.eventObserve(viewLifecycleOwner) {
            viewModel.fetchAllData()
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAllData()
    }

    private fun searchData(query: String){
        val searchQuery = "%$query%"
        lifecycleScope.launch(dispatcherController.launchInMain()){
            viewModel.searchData(searchQuery)
        }
    }
}