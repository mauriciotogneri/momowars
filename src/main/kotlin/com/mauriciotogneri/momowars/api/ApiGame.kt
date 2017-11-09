package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.database.DatabaseGame
import com.mauriciotogneri.momowars.exception.CustomException
import com.mauriciotogneri.momowars.exception.ForbiddenException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.headerParam
import com.mauriciotogneri.momowars.utils.launch

class ApiGame
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

                response
                        .status(200)
                        .json(documentGame.toJson())
            }
            catch (exception: Throwable)
            {
                CustomException.process(exception, response)
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
                CustomException.process(exception, response)
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
                CustomException.process(exception, response)
            }
        }
    }

    fun getOpenGames(request: Request, response: Response)
    {
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                CustomException.process(exception, response)
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
                CustomException.process(exception, response)
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
                CustomException.process(exception, response)
            }
        }
    }
}
