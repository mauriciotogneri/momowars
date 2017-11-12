package com.mauriciotogneri.momowars.constants

enum class CellType
{
    BASE,
    GRASS,
    EARTH,
    MOUNTAIN,
    FOREST,
    DESERT,
    CONCRETE,
    WATER,
    ICE;

    fun value() = toString().toLowerCase()
}