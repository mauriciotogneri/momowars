package com.mauriciotogneri.momowars.model

import com.mauriciotogneri.momowars.document.DocumentPlayer
import kotlin.js.Json
import kotlin.js.json

class ModelPlayer(private val documentPlayer: DocumentPlayer)
{
    fun toJson(): Json
    {
        return json(
                Pair("nickname", documentPlayer.account.nickname),
                Pair("color", documentPlayer.color),
                Pair("status", documentPlayer.status)
        )
    }

    /*players: [
	{
		nickname: string,
		color: string,
		status: turn_status,

		cells: int,
		bases: int,
		units: int
	}
]*/
}