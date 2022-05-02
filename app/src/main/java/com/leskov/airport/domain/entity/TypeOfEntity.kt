package com.leskov.airport.domain.entity

object TypeOfEntity {
    const val AIRPORT = "Airport"
    const val AIRCOMPANY = "AirCompany"
    const val AIRPLANE = "Airplane"
    const val RACE = "Race"
    const val ROUTE = "Route"
    const val TEAM = "Team"
    const val HEADQUARTERS = "Headquarters"
    const val INSURANCE = "Insurance"

    enum class Airport(val value: String) {
        TITLE("airport_title"),
        CITY("airport_city"),
        TERMINALS("airport_terminals"),
        LOCATION("airport_location"),
        LANES("airport_lanes")
    }

    enum class AirCompany(val value: String) {
        NAME("aircompany_name"),
        OFFICE("aircompany_office"),
        NUMBER("aircompany_number"),
        TYPE("aircompany_type"),
        LINES("aircompany_lines")
    }

    enum class Airplane(val value: String) {
        TITLE("airplane_title"),
        MODEL("airplane_model"),
        TYPE("airplane_type"),
        NUMBER("airplane_number"),
        CAPACITY("airplane_load_capacity")
    }

    enum class Race(val value: String) {
        TITLE("race_title"),
        DEPARTURE("race_time_departure"),
        FLIGHT("race_flight"),
        ARRIVAL("race_arrival_time"),
        NUMBER("race_number_of_race")
    }

    enum class Route(val value: String) {
        STATUS("route_status"),
        DEPARTURE("route_departute"),
        DESTINATION("route_destination"),
        LENGTH("route_length"),
        NUMBER("route_number")
    }

    enum class Team(val value: String) {
        NUMBER("team_number"),
        MOVERS("team_movers"),
        PILOTS("team_pilots"),
        ATTENDANTS("team_attendants"),
        ENGINEERS("team_engineers")
    }

    enum class Headquarters(val value: String) {
        NUMBER("headquarters_number"),
        AVAILABILITY("headquarters_availability"),
        BEDS("headquarters_beds"),
        LEVELS("headquarters_levels"),
        ENTERTAINMENT("headquarters_entertainment")
    }

    enum class Insurance(val value: String) {
        NAME("insurance_name"),
        TERM("insurance_term"),
        FORM("insurance_form"),
        PRICE("insurance_price"),
        TYPE("insurance_type")
    }
}

val listOfTypes = arrayOf(
    TypeOfEntity.AIRPORT,
    TypeOfEntity.AIRCOMPANY,
    TypeOfEntity.AIRPLANE,
    TypeOfEntity.RACE,
    TypeOfEntity.ROUTE,
    TypeOfEntity.TEAM,
    TypeOfEntity.HEADQUARTERS,
    TypeOfEntity.INSURANCE
)