package com.ianarbuckle.conferencesapi.models

import org.bson.codecs.pojo.annotations.BsonId

data class Conference(
    @BsonId
    val _id: String? = null,
    val name: String,
    val logoUrl: String,
    val location: Location,
    val dates: Dates,
    val callForPapers: CallForPapers,
    val status: String
)

data class Location(
    val country: Country,
    val venue: Venue
)

data class Country(
    val name: String,
    val city: String
)

data class Venue(
    val name: String,
    val address: String,
    val latLng: LatLng
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)

data class Dates(
    val startDate: String,
    val endDate: String
)

data class CallForPapers(
    val startDate: String,
    val endDate: String,
    val websiteUrl: String
)