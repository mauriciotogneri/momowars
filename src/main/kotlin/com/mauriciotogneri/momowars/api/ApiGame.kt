package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.constants.GameStatus
import com.mauriciotogneri.momowars.constants.PlayerStatus
import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.database.DatabaseGame
import com.mauriciotogneri.momowars.exception.ForbiddenException
import com.mauriciotogneri.momowars.exception.NotFoundException
import com.mauriciotogneri.momowars.exception.PreconditionFailedException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.headerParam
import com.mauriciotogneri.momowars.express.pathParam

class ApiGame : BaseApi()
{
    fun getGame(request: Request, response: Response)
    {
        process(response)
        {
            val sessionToken = request.headerParam(Api.SESSION_TOKEN)
            val gameId = request.pathParam("gameId")

            checkNotEmpty(sessionToken, gameId)

            val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

            val gameRef = documentAccount.gameRef(gameId) ?: throw ForbiddenException()

            val documentGame = DatabaseGame.byRef(gameRef)

            response.status(200).json(documentGame.toJson())
        }
    }

    fun endTurn(request: Request, response: Response)
    {
        process(response)
        {
            val sessionToken = request.headerParam(Api.SESSION_TOKEN)
            val gameId = request.pathParam("gameId")

            checkNotEmpty(sessionToken, gameId)

            val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

            val gameRef = documentAccount.gameRef(gameId) ?: throw ForbiddenException()

            val documentGame = DatabaseGame.byRef(gameRef)

            if (documentGame.status() != GameStatus.PLAYING.value())
            {
                throw PreconditionFailedException()
            }

            val documentPlayer = documentGame.playerForAccount(documentAccount) ?: throw NotFoundException()

            if (documentPlayer.status() != PlayerStatus.PLAYING.value())
            {
                throw PreconditionFailedException()
            }

            documentPlayer.endTurn()

            response.status(200).send()
        }
    }

    fun createGame(request: Request, response: Response)
    {
        // map_id
        // max_players

        process(response)
        {
            response.status(501).send()
        }
    }

    fun getOpenGames(request: Request, response: Response)
    {
        process(response)
        {
            val games = DatabaseGame.getOpenGames()
            val list = games.map { it.basicJson() }

            response.status(200).json(list)
        }
    }

    fun joinGame(request: Request, response: Response)
    {
        process(response)
        {
            response.status(501).send()
        }
    }

    fun leaveGame(request: Request, response: Response)
    {
        process(response)
        {
            response.status(501).send()
        }
    }
}
