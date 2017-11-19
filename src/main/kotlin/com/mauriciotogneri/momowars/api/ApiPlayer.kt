package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.constants.GameStatus
import com.mauriciotogneri.momowars.constants.PlayerStatus
import com.mauriciotogneri.momowars.exception.ForbiddenException
import com.mauriciotogneri.momowars.exception.InternalServerErrorException
import com.mauriciotogneri.momowars.exception.NotFoundException
import com.mauriciotogneri.momowars.exception.PreconditionFailedException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.headerParam
import com.mauriciotogneri.momowars.express.pathParam

class ApiPlayer : BaseApi()
{
    fun endTurn(request: Request, response: Response)
    {
        process(response) { database ->

            val sessionToken = request.headerParam(Api.SESSION_TOKEN)
            val gameId = request.pathParam("gameId")
            val playerId = request.pathParam("playerId")

            checkNotEmpty(sessionToken)

            val documentAccount = database.account.bySessionToken(sessionToken)

            val gameRef = documentAccount.gameRef(gameId) ?: throw ForbiddenException()

            val documentGame = database.game.byRef(gameRef)

            if (documentGame.status() != GameStatus.PLAYING.value())
            {
                throw PreconditionFailedException()
            }

            val documentPlayer = documentGame.playerForAccount(documentAccount) ?: throw NotFoundException()

            if (documentPlayer.id() != playerId)
            {
                throw ForbiddenException()
            }

            if (documentPlayer.status() != PlayerStatus.PLAYING.value())
            {
                throw PreconditionFailedException()
            }

            documentPlayer.endTurn()

            response.status(NO_CONTENT).send()
        }
    }

    fun joinGame(request: Request, response: Response)
    {
        process(response)
        {
            throw InternalServerErrorException()
        }
    }

    fun leaveGame(request: Request, response: Response)
    {
        process(response)
        {
            throw InternalServerErrorException()
        }
    }

}