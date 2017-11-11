package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.database.DatabaseGame
import com.mauriciotogneri.momowars.exception.ForbiddenException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.headerParam
import com.mauriciotogneri.momowars.utils.launch

class ApiGame: BaseApi()
{
    fun getGame(request: Request, response: Response)
    {
        launch {
            try
            {
                val sessionToken = request.headerParam(Api.SESSION_TOKEN)
                val gameId = request.param("gameId")

                val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

                val gameRef = documentAccount.gameRef(gameId) ?: throw ForbiddenException()

                val documentGame = DatabaseGame.byRef(gameRef)

                response.status(200).json(documentGame.toJson())
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }

    fun endTurn(request: Request, response: Response)
    {
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }

    fun createGame(request: Request, response: Response)
    {
        // map_id
        // max_players
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }

    fun getOpenGames(request: Request, response: Response)
    {
        launch {
            try
            {
                val games = DatabaseGame.getOpenGames()
                val list = games.map { it.basicJson() }

                response.status(200).json(list)
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }

    fun joinGame(request: Request, response: Response)
    {
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }

    fun leaveGame(request: Request, response: Response)
    {
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }
}
