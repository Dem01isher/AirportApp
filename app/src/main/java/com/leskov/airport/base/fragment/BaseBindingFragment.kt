package com.leskov.airport.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.leskov.airport.R


abstract class BaseBindingFragment<Binding : ViewDataBinding> : Fragment() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected val navController by lazy {
        (requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}