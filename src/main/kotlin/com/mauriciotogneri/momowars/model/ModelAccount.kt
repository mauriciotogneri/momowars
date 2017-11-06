package com.mauriciotogneri.momowars.model

import com.mauriciotogneri.momowars.document.DocumentAccount
import kotlin.js.Json
import kotlin.js.json

class ModelAccount(private val documentAccount: DocumentAccount)
{
    fun toJson(): Json
    {
        return json(
                Pair("email", documentAccount.email),
                Pair("nickname", documentAccount.nickname),
                Pair("games", documentAccount.games.map { it.id })
        )
    }
}