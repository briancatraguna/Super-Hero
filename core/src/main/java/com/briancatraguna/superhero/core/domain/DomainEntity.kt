package com.briancatraguna.superhero.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainEntity(
    val isError: Boolean,
    val heroItems: List<HeroItem>?
): Parcelable

@Parcelize
data class HeroItem(
    val image: String?,
    val name: String?,
    val desc: String?,
    val strength: String?,
    val durability: String?,
    val combat: String?,
    val power: String?,
    val speed: String?,
    val intelligence: String?
): Parcelable
