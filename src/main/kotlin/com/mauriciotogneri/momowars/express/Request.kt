package com.mauriciotogneri.momowars.express

external class Request
{
    val body: dynamic

    fun param(name: String): String

    fun get(name: String): String

    fun query(name: String): String
}
