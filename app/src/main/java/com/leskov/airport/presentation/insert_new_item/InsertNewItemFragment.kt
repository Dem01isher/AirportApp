package com.leskov.airport.presentation.insert_new_item

import android.text.InputType
import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentInsertNewItemBinding
import com.leskov.airport.domain.entity.AirplaneEntity
import com.leskov.airport.domain.entity.AirportEntity
import com.leskov.airport.domain.entity.RaceEntity
import com.leskov.airport.domain.entity.TypeOfEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertNewItemFragment :
    BaseVMFragment<InsertNewItemViewModel, FragmentInsertNewItemBinding>() {

    override val layoutId: Int = R.layout.fragment_insert_new_item

    override val viewModel: InsertNewItemViewModel by viewModels()

    private var loadedItem: Any? = null

    override fun initListeners() {
        super.initListeners()

        binding.toolbar.title = arguments?.getString("type") ?: ""

        binding.btnConfirm.title.setText(R.string.confirm)

        when (arguments?.getString("type") ?: ""){
            TypeOfEntity.AIRPLANE -> {
                binding.title.inputType = InputType.TYPE_CLASS_NUMBER
                binding.headlineLayout.hint = getString(R.string.number_of_airplane)
                binding.producerLayout.updateVisibility(true)
                binding.producerLayout.hint = getString(R.string.producer_of_airplane)
                initSelectedTypeList(binding.tvSelectedType,
                    resources.getStringArray(R.array.type_of_airplanes).toList()
                )
                binding.modelLayout.hint = getString(R.string.model)
                binding.capacityLayout.hint = getString(R.string.capacity)
            }
            TypeOfEntity.RACE -> {
                binding.headlineLayout.hint = getString(R.string.type_of_race)
            }
            TypeOfEntity.AIRCOMPANY -> {

            }
            TypeOfEntity.AIRPORT -> {
                binding.groupTime.updateVisibility(true)
                binding.typeLayout.updateVisibility(false)
                binding.headlineLayout.hint = getString(R.string.name_of_airport)
                binding.modelLayout.hint = getString(R.string.country_location)
                binding.capacityLayout.hint = getString(R.string.city)
                binding.timeTitle.text = getString(R.string.count_of_lanes) + " & " + getString(R.string.count_of_terminals)
                binding.timePickerLayout.hoursLayout.hint = getString(R.string.count_of_terminals)
                binding.timePickerLayout.minuteLayout.hint = getString(R.string.count_of_lanes)
            }
        }

        binding.btnConfirm.root.setOnClickWithDebounce {
            when (arguments?.getString("type") ?: ""){
                TypeOfEntity.AIRPORT -> {
                    viewModel.setType(TypeOfEntity.AIRPORT)
                    if (!isInputsFilled(
                            binding.title,
                            binding.model,
                            binding.capacity,
                            binding.timePickerLayout.hours,
                            binding.timePickerLayout.minute
                        )
                    ) {
                        loadedItem = AirportEntity(
                            title = binding.title.text.toString(),
                            countryLocation = binding.model.text.toString(),
                            city = binding.capacity.text.toString(),
                            countOfTerminals = binding.timePickerLayout.hours.text.toString().toInt(),
                            numberOfLanes = binding.timePickerLayout.minute.text.toString().toInt()
                        )
                        viewModel.insertNewItem(loadedItem)
                    } else return@setOnClickWithDebounce
                }
                TypeOfEntity.AIRPLANE -> {
                    viewModel.setType(TypeOfEntity.AIRPLANE)
                    if (!isInputsFilled(
                            binding.title,
                            binding.producer,
                            binding.model,
                            binding.capacity
                        )
                    ) {
                        loadedItem = AirplaneEntity(
                            number = binding.title.text.toString().toInt(),
                            producer = binding.producer.text.toString(),
                            type = binding.tvSelectedType.text.toString(),
                            model = binding.model.text.toString(),
                            loadCapacity = binding.capacity.text.toString(),
                        )
                        viewModel.insertNewItem(loadedItem)
                    } else return@setOnClickWithDebounce
                }
                TypeOfEntity.RACE -> {
                    viewModel.setType(TypeOfEntity.RACE)
                    loadedItem = RaceEntity(
                        0,
                        timeOfDeparture = "${binding.timePickerLayout.hours.text}:${binding.timePickerLayout.minute.text}",
                        arrivalTime = "${binding.timePickerLayout.hours.text}:${binding.timePickerLayout.minute.text}",
                        flightTime = "${binding.timePickerLayout.hours.text}:${binding.timePickerLayout.minute.text}",
                        typeOfRace = binding.title.text.toString()
                    )
                    viewModel.insertNewItem(loadedItem)
                }
                else -> {
                    // something
                }
            }
            navController.popBackStack()
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}