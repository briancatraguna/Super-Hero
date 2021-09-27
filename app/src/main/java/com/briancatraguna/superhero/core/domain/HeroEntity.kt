package com.briancatraguna.superhero.core.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeroEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "name")
    val name: String
)
