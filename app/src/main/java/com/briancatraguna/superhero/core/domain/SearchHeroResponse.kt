package com.briancatraguna.superhero.core.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SearchHeroResponse(

	@field:SerializedName("results-for")
	val resultsFor: String? = null,

	@field:SerializedName("response")
	val response: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class Image(

	@field:SerializedName("url")
	val url: String? = null
)

data class Powerstats(

	@field:SerializedName("strength")
	val strength: String? = null,

	@field:SerializedName("durability")
	val durability: String? = null,

	@field:SerializedName("combat")
	val combat: String? = null,

	@field:SerializedName("power")
	val power: String? = null,

	@field:SerializedName("speed")
	val speed: String? = null,

	@field:SerializedName("intelligence")
	val intelligence: String? = null
)

data class Connections(

	@field:SerializedName("relatives")
	val relatives: String? = null,

	@field:SerializedName("group-affiliation")
	val groupAffiliation: String? = null
)

data class Work(

	@field:SerializedName("occupation")
	val occupation: String? = null,

	@field:SerializedName("base")
	val base: String? = null
)

data class Appearance(

	@field:SerializedName("eye-color")
	val eyeColor: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("race")
	val race: String? = null,

	@field:SerializedName("weight")
	val weight: List<String?>? = null,

	@field:SerializedName("height")
	val height: List<String?>? = null,

	@field:SerializedName("hair-color")
	val hairColor: String? = null
)

//TODO Make it parcelable
data class ResultsItem(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("appearance")
	val appearance: Appearance? = null,

	@field:SerializedName("work")
	val work: Work? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("powerstats")
	val powerstats: Powerstats? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("biography")
	val biography: Biography? = null,

	@field:SerializedName("connections")
	val connections: Connections? = null
)

data class Biography(

	@field:SerializedName("place-of-birth")
	val placeOfBirth: String? = null,

	@field:SerializedName("aliases")
	val aliases: List<String?>? = null,

	@field:SerializedName("first-appearance")
	val firstAppearance: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("alignment")
	val alignment: String? = null,

	@field:SerializedName("full-name")
	val fullName: String? = null,

	@field:SerializedName("alter-egos")
	val alterEgos: String? = null
)
