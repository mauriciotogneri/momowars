package com.mauriciotogneri.momowars.express

object Parameter
{
    fun string(value: dynamic): String = if (value == null) "" else "$value"
}