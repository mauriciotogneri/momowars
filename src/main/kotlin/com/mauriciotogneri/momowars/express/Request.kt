package com.mauriciotogneri.momowars.express

external class Request
{
    val body: dynamic

    fun param(name: String): String

    fun get(name: String): String

    fun query(name: String): String
}

fun Request.bodyParam(name: String): String = cleanString(body[name])

fun Request.headerParam(name: String): String = cleanString(get(name))

fun cleanString(value: dynamic): String = if (value == null) "" else "$value"