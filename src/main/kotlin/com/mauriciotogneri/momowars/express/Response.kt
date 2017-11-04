package com.mauriciotogneri.momowars.express

external class Response
{
    fun status(code: Int): Response

    fun send(value: dynamic): Response
}
