package com.mauriciotogneri.momowars.express

external class Request
{
    val body: dynamic

    fun get(name: String): String

    fun query(name: String): String
}
