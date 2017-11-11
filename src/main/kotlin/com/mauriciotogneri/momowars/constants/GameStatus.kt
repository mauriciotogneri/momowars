package com.mauriciotogneri.momowars.constants

enum class GameStatus
{
    OPEN,
    PLAYING,
    CALCULATING,
    FINISHED;

    fun value() = toString().toLowerCase()
}