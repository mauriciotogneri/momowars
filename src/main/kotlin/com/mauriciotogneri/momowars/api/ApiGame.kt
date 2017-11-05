package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.database.DatabaseGame
import com.mauriciotogneri.momowars.database.DatabasePlayer
import com.mauriciotogneri.momowars.exception.CustomException
import com.mauriciotogneri.momowars.exception.ForbiddenException
import com.mauriciotogneri.momowars.express.Parameter
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.model.ModelGame
import com.mauriciotogneri.momowars.utils.launch

class ApiGame
{
    fun getGame(request: Request, response: Response)
    {
        launch {
            try
            {
                val sessionToken = Parameter.string(request.get(Api.SESSION_TOKEN))
                val gameId = request.param("gameId")

                val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

                val gameRef = documentAccount.gameRef(gameId)

                if (gameRef != null)
                {
                    val documentGame = DatabaseGame.byRef(gameRef)
                    val documentsPlayer = DatabasePlayer.byGameRef(gameRef)

                    response
                            .status(200)
                            .json(ModelGame(documentGame, documentsPlayer).toJson())
                }
                else
                {
                    throw ForbiddenException()
                }
            }
            catch (exception: Throwable)
            {
                CustomException.process(exception, response)
            }
        }
    }
}
