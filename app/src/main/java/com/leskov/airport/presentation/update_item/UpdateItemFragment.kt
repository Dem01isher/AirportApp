package com.leskov.airport.presentation.update_item

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateItemBinding
import com.leskov.airport.domain.entity.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateItemFragment : BaseVMFragment<UpdateItemViewModel, FragmentUpdateItemBinding>() {

    override val layoutId: Int = R.layout.fragment_update_item

    override val viewModel: UpdateItemViewModel by viewModels()

    private lateinit var loadedItem: Any

    override fun initListeners() {
        super.initListeners()

        viewModel.setType(arguments?.getString("type") ?: "")

        viewModel.getItemByKey(arguments?.get("key"))

        binding.toolbar.title = arguments?.getString("type") ?: ""

        binding.btnConfirm.title.setText(R.string.upate)

        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            when (arguments?.getString("type") ?: "") {

                TypeOfEntity.AIRCOMPANY -> {
                    viewModel.setType(TypeOfEntity.AIRCOMPANY)
                    if (!isInputsFilled(
                            binding.title,
                            binding.model,
                            binding.datePickerLayout.day,
                            binding.datePickerLayout.month,
                            binding.datePickerLayout.year,
                            binding.capacity,
                            binding.timePickerLayout.hours
                        )
                    ) {
                        loadedItem = AirCompanyEntity(
                            nameOf = binding.title.text.toString(),
                            officeLocation = binding.model.text.toString(),
                            typeOf = binding.tvSelectedType.text.toString(),
                            dateOfFoundation = "${binding.datePickerLayout.day.text}:${binding.datePickerLayout.month.text}:${binding.datePickerLayout.year.text}",
                            contactNumber = binding.capacity.text.toString(),
                            countOfLanes = binding.timePickerLayout.hours.text.toString().toInt()
                        )
                        viewModel.updateSelectedTypeData(loadedItem)
                    } else return@setOnClickWithDebounce
                }

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
                            countOfTerminals = binding.timePickerLayout.hours.text.toString()
                                .toInt(),
                            numberOfLanes = binding.timePickerLayout.minute.text.toString().toInt()
                        )
                        viewModel.updateSelectedTypeData(loadedItem)
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
                        viewModel.updateSelectedTypeData(loadedItem)
                    } else return@setOnClickWithDebounce
                }
                TypeOfEntity.RACE -> {
                    viewModel.setType(TypeOfEntity.RACE)
                    if (!isInputsFilled(
                            binding.capacity,
                            binding.model,
                            binding.producer,
                            binding.title,
                            binding.timePickerLayout.hours,
                            binding.timePickerLayout.minute
                        )
                    ) {
                        loadedItem = RaceEntity(
                            numberOfRace = binding.capacity.text.toString().toInt(),
                            timeOfDeparture = binding.model.text.toString(),
                            arrivalTime = "${binding.timePickerLayout.hours.text}:${binding.timePickerLayout.minute.text}",
                            flightTime = binding.producer.text.toString(),
                            typeOfRace = binding.title.text.toString()
                        )
                        viewModel.updateSelectedTypeData(loadedItem)
                    } else return@setOnClickWithDebounce
                }
                TypeOfEntity.INSURANCE -> {
                    viewModel.setType(TypeOfEntity.INSURANCE)
                    if (!isInputsFilled(
                            binding.title,
                            binding.model,
                            binding.datePickerLayout.day,
                            binding.datePickerLayout.month,
                            binding.datePickerLayout.year,
                            binding.producer
                        )
                    ) {
                        loadedItem = InsuranceEntity(
                            typeOf = binding.tvSelectedType.text.toString(),
                            serviceName = binding.title.text.toString(),
                            binding.model.text.toString().toInt(),
                            term = "${binding.datePickerLayout.day.text}:${binding.datePickerLayout.month.text}:${binding.datePickerLayout.year.text}",
                            formOfInsurance = binding.producer.text.toString()
                        )
                        viewModel.updateSelectedTypeData(loadedItem)
                    } else return@setOnClickWithDebounce
                }
                TypeOfEntity.TEAM -> {
                    viewModel.setType(TypeOfEntity.TEAM)
                    if (!isInputsFilled(
                            binding.title,
                            binding.model,
                            binding.capacity,
                            binding.producer
                        )
                    ) {
                        loadedItem = TeamEntity(
                            numberOf = binding.title.text.toString().toInt(),
                            countOfPeople = binding.model.text.toString()
                                .toInt() + binding.model.text.toString()
                                .toInt() + binding.capacity.text.toString()
                                .toInt() + binding.producer.text.toString().toInt(),
                            countOfPilots = binding.model.text.toString().toInt(),
                            countOfEngineers = binding.producer.text.toString().toInt(),
                            countFlightAttendants = binding.capacity.text.toString().toInt(),
                            numberOfMovers = binding.producer.text.toString().toInt()
                        )
                        viewModel.updateSelectedTypeData(loadedItem)
                    } else return@setOnClickWithDebounce
                }
                TypeOfEntity.ROUTE -> {
                    viewModel.setType(TypeOfEntity.ROUTE)
                    if (!isInputsFilled(
                            binding.title,
                            binding.model,
                            binding.capacity,
                            binding.producer,
                            binding.timePickerLayout.hours
                        )
                    ) {
                        loadedItem = RouteEntity(
                            numberOf = binding.title.text.toString().toInt(),
                            status = binding.model.text.toString(),
                            departureCountry = binding.capacity.text.toString(),
                            destinationCountry = binding.producer.text.toString(),
                            binding.timePickerLayout.hours.text.toString()
                        )
                        viewModel.updateSelectedTypeData(loadedItem)
                    } else return@setOnClickWithDebounce
                }
                TypeOfEntity.HEADQUARTERS -> {
                    viewModel.setType(TypeOfEntity.HEADQUARTERS)
                    if (!isInputsFilled(binding.title, binding.capacity, binding.model)) {
                        loadedItem = HeadQuarterEntity(
                            numberOf = binding.title.text.toString().toInt(),
                            availabilityOfKitchen = if (binding.cbAvailabilityOfKitchen.isChecked) getString(R.string.kitchen_is_available) else getString(R.string.kitchen_is_disabled),
                            countOfLevels = binding.capacity.text.toString().toInt(),
                            numberOfBeds = binding.model.text.toString().toInt(),
                            entertainmentRoom = if (binding.cbEntertainmentRoom.isChecked) getString(R.string.have_entertainment_room) else getString(R.string.no_entertainment_room)
                        )
                        viewModel.updateSelectedTypeData(loadedItem)
                    } else return@setOnClickWithDebounce
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

    override fun initObservers() {
        super.initObservers()

        viewModel.selectedType.nonNullObserve(viewLifecycleOwner){
            
        }

        viewModel.updateItem.nonNullObserve(viewLifecycleOwner){
            when (arguments?.getString("type") ?: ""){
                TypeOfEntity.AIRPLANE -> {
                    val key = it as AirplaneEntity
                    binding.producer.setText(key.producer)
                    binding.title.setText(key.number)
                    binding.tvSelectedType.setText(key.type)
                    binding.model.setText(key.model)
                    binding.capacity.setText(key.loadCapacity)
                }
                TypeOfEntity.AIRPORT -> {
                    val key = it as AirportEntity
                    binding.title.setText(key.title)
                    binding.model.setText(key.countryLocation)
                    binding.capacity.setText(key.city)
                    binding.timePickerLayout.hours.setText(key.countOfTerminals)
                    binding.timePickerLayout.minute.setText(key.numberOfLanes)
                }
                TypeOfEntity.RACE -> {
                    val key = it as RaceEntity
                    binding.capacity.setText(key.numberOfRace)
                    binding.model.setText(key.timeOfDeparture)
                    binding.producer.setText(key.flightTime)
                    binding.title.setText(key.typeOfRace)
                }
                TypeOfEntity.TEAM -> {
                    val key = it as TeamEntity
                    binding.title.setText(key.numberOf)
                    binding.model.setText(key.countOfPilots)
                    binding.producer.setText(key.countOfEngineers)
                    binding.capacity.setText(key.countFlightAttendants)
                    binding.producer.setText(key.numberOfMovers)
                }
                TypeOfEntity.INSURANCE -> {
                    val key = it as InsuranceEntity

                }
                TypeOfEntity.HEADQUARTERS -> {
                    val key = it as HeadQuarterEntity
                    binding.title.setText(key.numberOf)
                    binding.capacity.setText(key.countOfLevels)
                    binding.model.setText(key.numberOfBeds)
                }
                TypeOfEntity.ROUTE -> {
                    val key = it as RouteEntity
                    binding.title.setText(key.numberOf)
                    binding.model.setText(key.status)
                    binding.capacity.setText(key.departureCountry)
                    binding.producer.setText(key.destinationCountry)
                    binding.timePickerLayout.hours.setText(key.length)
                }
                TypeOfEntity.AIRCOMPANY -> {
                    val key = it as AirCompanyEntity
                    binding.title.setText(key.nameOf)
                    binding.model.setText(key.officeLocation)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateItem.removeObservers(viewLifecycleOwner)
    }
}