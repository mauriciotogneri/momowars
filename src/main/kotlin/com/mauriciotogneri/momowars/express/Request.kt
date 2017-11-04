package com.mauriciotogneri.momowars.express

external class Request
{
    val body: dynamic

    fun query(name: String): String
}
