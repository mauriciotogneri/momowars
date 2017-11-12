package com.mauriciotogneri.momowars.constants

enum class UnitType
{
    SQUAD,
    MORTAR,
    HUMVEE,
    LIGHT_TANK,
    HEAVY_TANK,
    HELICOPTER;

    fun value() = toString().toLowerCase()
}