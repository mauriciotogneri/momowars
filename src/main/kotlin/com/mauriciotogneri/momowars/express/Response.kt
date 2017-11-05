package com.mauriciotogneri.momowars.express

external class Response
{
    fun status(code: Int): Response

    fun set(header: String, value: String): Response

    fun send(value: String): Response

    fun json(value: dynamic): Response

    fun send(): Nothing
}
