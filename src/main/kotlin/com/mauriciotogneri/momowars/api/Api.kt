package com.mauriciotogneri.momowars.api

object Api
{
    val session = ApiSession()
    val account = ApiAccount()
    val game = ApiGame()
    val player = ApiPlayer()
    val unit = ApiUnit()
    val map = ApiMap()

    val SESSION_TOKEN = "Session-Token"
}
