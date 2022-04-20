package com.leskov.airport.presentation.main_menu

import androidx.core.os.bundleOf
import com.leskov.airport.R
import com.leskov.airport.base.fragment.BaseBindingFragment
import com.leskov.airport.databinding.FragmentMainMenuBinding
import com.leskov.airport.domain.model.MainMenuModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : BaseBindingFragment<FragmentMainMenuBinding>() {

    override val layoutId: Int = R.layout.fragment_main_menu

    private val menuAdapter = MainMenuAdapter()


    private val listOfItems : List<MainMenuModel> = listOf(
        MainMenuModel("Airport", 0),
        MainMenuModel("Race", 0),
        MainMenuModel("Office", 0)
    )

    override fun initListeners() {
        super.initListeners()

        binding.lvMenu.adapter = menuAdapter
        binding.lvMenu.setHasFixedSize(true)

        menuAdapter.submitList(listOfItems)

        menuAdapter.onItemClickListener {
            navController.navigate(
                R.id.action_mainMenuFragment_to_listOfItemsFragment,
                bundleOf("title" to it)
            )
        }
    }
}