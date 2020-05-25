package sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Conference(
    val id: String,
    val name: String,
    val logoUrl: String,
    val location: Location,
    val dates: Dates,
    val callForPapers: CallForPapers,
    val status: String
)

@Serializable
data class Location(
    val country: Country,
    val venue: Venue
)

@Serializable
data class Country(
    val name: String,
    val city: String
)

@Serializable
data class Venue(
    val name: String,
    val address: String,
    val latLng: LatLng
)

@Serializable
data class LatLng(
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Dates(
    val startDate: String,
    val endDate: String
)

@Serializable
data class CallForPapers(
    val startDate: String,
    val endDate: String,
    val websiteUrl: String
)