package com.leskov.airport.presentation.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.leskov.airport.R
import com.leskov.airport.base.dialog.BaseBindingDialogFragment
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.databinding.DialogFragmentFiletrListBinding

class FilterListDialogFragment() : BaseBindingDialogFragment<DialogFragmentFiletrListBinding>() {

    override val layoutId: Int
        get() = R.layout.dialog_fragment_filetr_list

    override fun initListeners() {
        super.initListeners()

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.title.text = arguments?.getString("title") ?: ""
        binding.description.text = arguments?.getString("description") ?: ""

        binding.btnConfirm.setOnClickWithDebounce {
            setFragmentResult(
                "filter_request",
                bundleOf(
                    "filter_first" to binding.filterFirst.text.toString(),
                    "filter_second" to binding.filterSecond.text.toString()
                )
            )
            dismiss()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(title: String, description: String): FilterListDialogFragment =
            FilterListDialogFragment().apply {
                val bundle = Bundle()
                bundle.putString("title", title)
                bundle.putString("description", description)
                arguments = bundle
            }
    }
}