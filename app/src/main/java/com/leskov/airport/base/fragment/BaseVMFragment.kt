package com.leskov.airport.base.fragment

import androidx.databinding.ViewDataBinding
import com.leskov.airport.base.view_model.BaseViewModel


abstract class BaseVMFragment<VM : BaseViewModel, Binding : ViewDataBinding> :
    BaseBindingFragment<Binding>() {

    protected abstract val viewModel: VM

}