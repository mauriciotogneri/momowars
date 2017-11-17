package com.mauriciotogneri.momowars.document

import com.mauriciotogneri.momowars.constants.PlayerStatus
import com.mauriciotogneri.momowars.firebase.DocumentSnapshot
import com.mauriciotogneri.momowars.utils.await
import kotlin.js.Json
import kotlin.js.json

class DocumentPlayer(val doc: DocumentSnapshot, private val documentAccount: DocumentAccount)
{
    private val color: String = doc.data().color
    private val resources: Int = doc.data().resources
    private val status: String = doc.data().status

    fun id() = doc.id

    fun status() = status

    suspend fun endTurn()
    {
        val json = json()
        json["status"] = PlayerStatus.WAITING.value()

        doc.ref.update(json).await()
    }

    fun isOfAccount(account: DocumentAccount): Boolean
    {
        return account.id() == documentAccount.id()
    }

    fun toJson(): Json
    {
        val json = json()
        json["nickname"] = documentAccount.nickname()
        json["color"] = color
        json["status"] = status

        return json
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