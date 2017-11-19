package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.exception.ForbiddenException
import com.mauriciotogneri.momowars.exception.InternalServerErrorException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.headerParam
import com.mauriciotogneri.momowars.express.pathParam

class ApiGame : BaseApi()
{
    fun getGame(request: Request, response: Response)
    {
        process(response) { database ->
            val sessionToken = request.headerParam(Api.SESSION_TOKEN)
            val gameId = request.pathParam("gameId")

            checkNotEmpty(sessionToken)

            val documentAccount = database.account.bySessionToken(sessionToken)

            val gameRef = documentAccount.gameRef(gameId) ?: throw ForbiddenException()

            val documentGame = database.game.byRef(gameRef)

            response.status(OK).json(documentGame.toJson())
        }
    }

    fun createGame(request: Request, response: Response)
    {
        process(response)
        {
            throw InternalServerErrorException()
        }
    }

    fun getOpenGames(request: Request, response: Response)
    {
        process(response) { database ->

            val sessionToken = request.headerParam(Api.SESSION_TOKEN)

            checkNotEmpty(sessionToken)

            database.account.bySessionToken(sessionToken)

            val games = database.game.getOpenGames()
            val list = games.map { it.basicJson() }

            response.status(OK).json(list)
        }
    }
}
