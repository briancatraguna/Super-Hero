package com.briancatraguna.superhero.core.data.room

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
    val name: String,

    @ColumnInfo(name = "strength")
    val strength: String,

    @ColumnInfo(name = "durability")
    val durability: String,

    @ColumnInfo(name = "combat")
    val combat: String,

    @ColumnInfo(name = "power")
    val power: String,

    @ColumnInfo(name = "speed")
    val speed: String,

    @ColumnInfo(name = "intelligence")
    val intelligence: String
)
