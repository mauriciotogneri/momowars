package com.mauriciotogneri.momowars.api

object Api
{
    val session = ApiSession()
    val account = ApiAccount()
    val game = ApiGame()
    val unit = ApiUnits()
    val map = ApiMaps()

    val SESSION_TOKEN = "Session-Token"
}
