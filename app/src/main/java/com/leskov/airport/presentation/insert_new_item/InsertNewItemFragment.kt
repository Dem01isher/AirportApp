package com.leskov.airport.presentation.insert_new_item

import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.InputType
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.helper.PhoneTextFormatter
import com.leskov.airport.databinding.FragmentInsertNewItemBinding
import com.leskov.airport.domain.entity.*
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

        when (arguments?.getString("type") ?: "") {
            TypeOfEntity.AIRPLANE -> {
                binding.title.inputType = InputType.TYPE_CLASS_NUMBER
                binding.headlineLayout.hint = getString(R.string.number_of_airplane)
                binding.producerLayout.updateVisibility(true)
                binding.producerLayout.hint = getString(R.string.producer_of_airplane)
                initSelectedTypeList(
                    binding.tvSelectedType,
                    resources.getStringArray(R.array.type_of_airplanes).toList()
                )
                binding.modelLayout.hint = getString(R.string.model)
                binding.capacityLayout.hint = getString(R.string.capacity)
            }
            TypeOfEntity.RACE -> {
                binding.capacity.inputType = InputType.TYPE_CLASS_NUMBER
                binding.headlineLayout.hint = getString(R.string.type_of_race)
                binding.timeTitle.text = getString(R.string.arrival_time)
                binding.capacityLayout.hint = getString(R.string.number_of_race)
                initSelectedTypeList(
                    binding.tvSelectedType,
                    resources.getStringArray(R.array.type_of_race).asList()
                )
                binding.groupDate.updateVisibility(false)
                binding.groupTime.updateVisibility(true)
                binding.modelLayout.hint = getString(R.string.departure_time)
                binding.producerLayout.updateVisibility(true)
                binding.producerLayout.hint = getString(R.string.flight_time)
            }
            TypeOfEntity.AIRCOMPANY -> {
                binding.groupTime.updateVisibility(true)
                binding.timeTitle.text = getString(R.string.count_of_lanes)
                binding.timePickerLayout.minute.updateVisibility(false)
                binding.timePickerLayout.hoursLayout.hint = ""
                binding.timePickerLayout.dotsDivider.updateVisibility(false)
                binding.groupDate.updateVisibility(true)
                initSelectedTypeList(binding.tvSelectedType, resources.getStringArray(R.array.type_of_airlines).toList())
                binding.headlineLayout.hint = getString(R.string.name)
                binding.typeLayout.hint = getString(R.string.airline_type)
                binding.modelLayout.hint = getString(R.string.office_location)
                binding.dateTitle.text = getString(R.string.foundation_date)
                binding.capacityLayout.hint = getString(R.string.contact_number)
                binding.capacity.inputType = InputType.TYPE_CLASS_PHONE
            }
            TypeOfEntity.AIRPORT -> {
                binding.groupTime.updateVisibility(true)
                binding.typeLayout.updateVisibility(false)
                binding.headlineLayout.hint = getString(R.string.name_of_airport)
                binding.modelLayout.hint = getString(R.string.country_location)
                binding.capacityLayout.hint = getString(R.string.city)
                binding.timeTitle.text =
                    getString(R.string.count_of_lanes) + " & " + getString(R.string.count_of_terminals)
                binding.timePickerLayout.hoursLayout.hint = getString(R.string.count_of_terminals)
                binding.timePickerLayout.minuteLayout.hint = getString(R.string.count_of_lanes)

                binding.title.setText(arguments?.getString("airport_title") ?: "")
                binding.model.setText(arguments?.getString("airport_location") ?: "")
                binding.capacity.setText(arguments?.getString("airport_city") ?: "")
                binding.timePickerLayout.hours.setText(arguments?.getString("airport_terminals") ?: "")
                binding.timePickerLayout.minute.setText(arguments?.getString("airport_lanes") ?: "")
            }
            TypeOfEntity.INSURANCE -> {
                binding.typeLayout.hint = getString(R.string.type_of_insurance)
                binding.headlineLayout.hint = getString(R.string.service_name)
                binding.modelLayout.hint = getString(R.string.service_price)
                binding.model.inputType = InputType.TYPE_CLASS_NUMBER
                binding.dateTitle.text = getString(R.string.term)
                binding.groupTime.updateVisibility(false)
                binding.groupDate.updateVisibility(true)
                binding.producerLayout.updateVisibility(true)
                binding.producerLayout.hint = getString(R.string.form_of_insurance)
            }
            TypeOfEntity.TEAM -> {
                binding.groupTime.updateVisibility(false)
                binding.groupDate.updateVisibility(false)
                binding.headlineLayout.hint = getString(R.string.crew_number)
                binding.title.inputType = InputType.TYPE_CLASS_NUMBER
                binding.title.maxEms = 3
                binding.typeLayout.updateVisibility(false)
                binding.modelLayout.hint = getString(R.string.number_of_pilots)
                binding.model.inputType = InputType.TYPE_CLASS_NUMBER
                binding.capacityLayout.hint = getString(R.string.number_of_flight_attendants)
                binding.capacity.inputType = InputType.TYPE_CLASS_NUMBER
                binding.producerLayout.updateVisibility(true)
                binding.producerLayout.hint = getString(R.string.number_of_engineers)
                binding.producer.inputType = InputType.TYPE_CLASS_NUMBER
            }
            TypeOfEntity.ROUTE -> {
                binding.groupDate.updateVisibility(false)
                binding.headlineLayout.hint = getString(R.string.number_of_route)
                binding.title.inputType = InputType.TYPE_CLASS_NUMBER
                binding.typeLayout.updateVisibility(false)
                binding.modelLayout.hint = getString(R.string.route_status)
                binding.capacityLayout.hint = getString(R.string.departure_of_country)
                binding.producerLayout.updateVisibility(true)
                binding.producerLayout.hint = getString(R.string.destination_country)
                binding.timeTitle.text = getString(R.string.length)
                binding.groupTime.updateVisibility(true)
                binding.timePickerLayout.dotsDivider.updateVisibility(false)
                binding.timePickerLayout.minute.updateVisibility(false)
            }
            TypeOfEntity.HEADQUARTERS -> {
                binding.groupDate.updateVisibility(false)
                binding.groupTime.updateVisibility(false)
                binding.groupServices.updateVisibility(true)
                binding.headlineLayout.hint = getString(R.string.number_of_headquarters)
                binding.title.inputType = InputType.TYPE_CLASS_NUMBER
                binding.typeLayout.updateVisibility(false)
                binding.modelLayout.hint = getString(R.string.count_of_beds)
                binding.model.inputType = InputType.TYPE_CLASS_NUMBER
                binding.capacityLayout.hint = getString(R.string.number_of_floors)
                binding.capacity.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }

        binding.btnConfirm.root.setOnClickWithDebounce {
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
                            binding.title.text.toString(),
                            binding.model.text.toString(),
                            binding.tvSelectedType.text.toString(),
                            dateOfFoundation = "${binding.datePickerLayout.day.text}:${binding.datePickerLayout.month.text}:${binding.datePickerLayout.year.text}",
                            contactNumber = binding.capacity.text.toString(),
                            countOfLanes = binding.timePickerLayout.hours.text.toString().toInt()
                        )
                        viewModel.insertNewItem(loadedItem)
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
                        viewModel.insertNewItem(loadedItem)
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
                        viewModel.insertNewItem(loadedItem)
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
                        viewModel.insertNewItem(loadedItem)
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
                        viewModel.insertNewItem(loadedItem)
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
                        viewModel.insertNewItem(loadedItem)
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

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }
}