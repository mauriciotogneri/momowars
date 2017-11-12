package com.mauriciotogneri.momowars.constants

enum class MovementType
{
    NONE,
    UP_LEFT,
    UP,
    UP_RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT;

    fun value() = toString().toLowerCase()
}