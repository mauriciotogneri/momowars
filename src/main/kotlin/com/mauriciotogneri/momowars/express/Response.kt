package com.mauriciotogneri.momowars.express

external class Response
{
    fun status(code: Int): Response

    fun set(header: String, value: String): Response

    fun send(value: dynamic): Response

    fun send(): Nothing
}
