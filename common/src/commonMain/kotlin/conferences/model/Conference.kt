package conferences.model

import kotlinx.serialization.Serializable

expect interface Parcelable

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()

@Parcelize
@Serializable
data class Conference(
    val id: String,
    val name: String,
    val logoUrl: String,
    val location: Location,
    val dates: Dates,
    val callForPapers: CallForPapers,
    val status: String
) : Parcelable

@Parcelize
@Serializable
data class Location(
    val country: Country,
    val venue: Venue
) : Parcelable

@Parcelize
@Serializable
data class Country(
    val name: String,
    val city: String
) : Parcelable

@Parcelize
@Serializable
data class Venue(
    val name: String,
    val address: String,
    val latLng: LatLng
) : Parcelable

@Parcelize
@Serializable
data class LatLng(
    val latitude: Double,
    val longitude: Double
) : Parcelable

@Parcelize
@Serializable
data class Dates(
    val startDate: String,
    val endDate: String
) : Parcelable

@Parcelize
@Serializable
data class CallForPapers(
    val startDate: String,
    val endDate: String,
    val websiteUrl: String
) : Parcelable