package com.leskov.airport.presentation.list_of_items

import android.text.Editable
import android.text.TextWatcher
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.SwipeToDeleteCallback
import com.leskov.airport.databinding.FragmentListOfItemsBinding
import com.leskov.airport.domain.entity.listOfTypes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfItemsFragment : BaseVMFragment<ListOfItemsViewModel, FragmentListOfItemsBinding>() {

    override val layoutId: Int = R.layout.fragment_list_of_items

    override val viewModel: ListOfItemsViewModel by viewModels()

    private val listOfItemsAdapter = ListOfItemsAdapter()

    override fun initListeners() {
        super.initListeners()

        viewModel.setType(arguments?.getString("title") ?: "")

        binding.toolbarTitle.text = arguments?.getString("title") ?: ""

        binding.rvListOfItems.adapter = listOfItemsAdapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = binding.rvListOfItems.adapter as ListOfItemsAdapter

                showAlertDialog(
                    getString(R.string.remove_item),
                    getString(R.string.you_sure_u_want_remove_item),
                    negativeAction = { adapter.notifyItemChanged(viewHolder.bindingAdapterPosition) },
                    positiveAction = {
                        viewModel.removeItemByType(adapter.currentList[viewHolder.bindingAdapterPosition])
                        adapter.notifyDataSetChanged()
                    }
                )
            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(binding.rvListOfItems)

        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) viewModel.searchDataByType(p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) viewModel.searchDataByType(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.flAddItem.setOnClickWithDebounce {
            navController.navigate(
                R.id.action_listOfItemsFragment_to_insertNewItemFragment,
                bundleOf("type" to arguments?.getString("title"))
            )
        }

        binding.btnMore.setOnClickWithDebounce {
            showAlertDialogWithList(getString(R.string.choose_table), listOfTypes) {
                viewModel.setType(listOfTypes[it])
                viewModel.fetchSelectedTypeData()
            }
        }

        binding.btnSearch.setOnClickWithDebounce {
            openSearch()
        }

        binding.ivCancelSearch.setOnClickWithDebounce {
            cancelSearch()
        }

        binding.arrowBack.setOnClickWithDebounce {
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.listOfData.eventObserve(viewLifecycleOwner) { list ->
            checkList(list, list.isEmpty())
        }
        viewModel.loading.eventObserve(viewLifecycleOwner) { loading ->
            binding.progressLayout.root.updateVisibility(loading)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchSelectedTypeData()
    }

    private fun checkList(list: List<Any?>, isEmpty: Boolean) {
        if (isEmpty) {
            binding.listStateLayout.updateVisibility(isEmpty)
            listOfItemsAdapter.currentList.clear()
            listOfItemsAdapter.notifyDataSetChanged()
        } else {
            binding.listStateLayout.updateVisibility(isEmpty)
            listOfItemsAdapter.currentList = list as MutableList<Any?>
            listOfItemsAdapter.notifyDataSetChanged()
        }
    }

    private fun openSearch() {
        binding.searchField.updateVisibility(true)
        binding.ivCancelSearch.updateVisibility(true)
        binding.arrowBack.updateVisibility(false)
        binding.btnSearch.updateVisibility(false)
        binding.toolbarTitle.updateVisibility(false)
        binding.btnMore.updateVisibility(false)
    }

    private fun cancelSearch() {
        binding.searchField.clearFocus()
        hideKeyboard(binding.searchField)
        binding.searchField.text.clear()
        binding.btnMore.updateVisibility(true)
        binding.ivCancelSearch.updateVisibility(false)
        binding.searchField.updateVisibility(false)
        binding.arrowBack.updateVisibility(true)
        binding.btnSearch.updateVisibility(true)
        binding.toolbarTitle.updateVisibility(true)
    }
}