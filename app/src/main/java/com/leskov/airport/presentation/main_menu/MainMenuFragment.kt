package com.leskov.airport.presentation.main_menu

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import com.leskov.airport.MainActivity
import com.leskov.airport.R
import com.leskov.airport.base.extensions.showAlertDialogWithList
import com.leskov.airport.base.fragment.BaseBindingFragment
import com.leskov.airport.databinding.FragmentMainMenuBinding
import com.leskov.airport.domain.entity.TypeOfEntity
import com.leskov.airport.domain.model.Languages
import com.leskov.airport.domain.model.MainMenuModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainMenuFragment : BaseBindingFragment<FragmentMainMenuBinding>() {

    override val layoutId: Int = R.layout.fragment_main_menu

    private val menuAdapter = MainMenuAdapter()

    private val listOfItems: List<MainMenuModel> = listOf(
        MainMenuModel(TypeOfEntity.AIRPLANE, R.drawable.ic_airplane),
        MainMenuModel(TypeOfEntity.RACE, R.drawable.ic_race),
        MainMenuModel(TypeOfEntity.AIRPORT, R.drawable.ic_airport)
    )

    @SuppressLint("ResourceType")
    override fun initListeners() {
        super.initListeners()

        val listOfLanguages = arrayOf(
            getString(R.string.ukrainian_language),
            getString(R.string.english_language)
        )

        binding.lvMenu.adapter = menuAdapter
        binding.lvMenu.setHasFixedSize(true)

        binding.toolbar.setOnMenuItemClickListener {

            val icon = if (sharedPreferenceManager.language == Languages.UKR) R.drawable.ic_ukraine_flag else R.drawable.ic_united_kingdom_flag

            when (it.itemId) {
                R.id.show_more_menu -> {
                    showAlertDialogWithList(
                        getString(R.string.choose_language),
                        listOfLanguages,
                        icon
                    ) { position ->
                        when (position){
                            0 -> {
                                if (sharedPreferenceManager.language == Languages.UKR) return@showAlertDialogWithList
                                sharedPreferenceManager.language = Languages.UKR
                                (requireActivity() as? MainActivity)?.changeLanguage()
                            }
                            1 -> {
                                if (sharedPreferenceManager.language == Languages.ENG) return@showAlertDialogWithList
                                sharedPreferenceManager.language = Languages.ENG
                                (requireActivity() as? MainActivity)?.changeLanguage()
                            }
                        }
                    }
                }
            }
            true
        }

        menuAdapter.submitList(listOfItems)

        menuAdapter.onItemClickListener {
            navController.navigate(
                R.id.action_mainMenuFragment_to_listOfItemsFragment,
                bundleOf("title" to it)
            )
        }
    }
}