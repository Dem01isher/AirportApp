package com.leskov.airport.presentation.dialog

import com.leskov.airport.R
import com.leskov.airport.base.dialog.BaseBindingDialogFragment
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.databinding.DialogFragmentMoreInfoBinding

class MoreInfoDialogFragment : BaseBindingDialogFragment<DialogFragmentMoreInfoBinding>() {

    override val layoutId: Int
        get() = R.layout.dialog_fragment_more_info

    override fun initListeners() {
        super.initListeners()

        binding.btnConfirm.setOnClickWithDebounce {
            dismiss()
        }
    }
}