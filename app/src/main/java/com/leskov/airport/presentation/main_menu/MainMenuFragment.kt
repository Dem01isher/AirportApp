package com.leskov.airport.presentation.main_menu

import android.annotation.SuppressLint
import com.leskov.airport.MainActivity
import com.leskov.airport.R
import com.leskov.airport.base.extensions.showAlertDialogWithList
import com.leskov.airport.base.fragment.BaseBindingFragment
import com.leskov.airport.databinding.FragmentMainMenuBinding
import com.leskov.airport.domain.model.Languages
import com.leskov.airport.domain.model.MainMenuItemType
import com.leskov.airport.presentation.dialog.MoreInfoDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainMenuFragment : BaseBindingFragment<FragmentMainMenuBinding>() {

    override val layoutId: Int = R.layout.fragment_main_menu

    private val menuAdapter = MainMenuAdapter()

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
            when (it.itemId) {
                R.id.show_more_menu -> {
                    showAlertDialogWithList(
                        getString(R.string.choose_language),
                        listOfLanguages,
                        if (sharedPreferenceManager.language == Languages.UKR) R.drawable.ic_ukraine_flag else R.drawable.ic_united_kingdom_flag
                    ) { position ->
                        when (position) {
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
                R.id.show_user_manual -> MoreInfoDialogFragment().show(requireActivity().supportFragmentManager, tag)
            }
            true
        }

        menuAdapter.setOnItemClickListener {
            when (menuAdapter.list[it]) {
                MainMenuItemType.AIRCOMPANY -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfAirCompaniesFragment)
                }
                MainMenuItemType.TEAM -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfTeamsFragment)
                }
                MainMenuItemType.ROUTE -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfRoutesFragment)
                }
                MainMenuItemType.RACE -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfRacesFragment)
                }
                MainMenuItemType.INSURANCE -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfInsurancesFragment)
                }
                MainMenuItemType.HEADQUARTERS -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfHeadquartersFragment)
                }
                MainMenuItemType.AIRPORT -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfAirportsFragment)
                }
                MainMenuItemType.AIRPLANE -> {
                    navController.navigate(R.id.action_mainMenuFragment_to_listOfAirplanesFragment)
                }
            }
        }
    }
}