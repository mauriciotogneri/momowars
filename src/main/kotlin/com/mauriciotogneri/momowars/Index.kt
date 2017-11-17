package com.mauriciotogneri.momowars

import com.mauriciotogneri.momowars.api.Api
import com.mauriciotogneri.momowars.database.Database
import com.mauriciotogneri.momowars.node.exports
import com.mauriciotogneri.momowars.node.require

fun main(args: Array<String>)
{
    val functions = require("firebase-functions")
    val admin = require("firebase-admin")
    admin.initializeApp(functions.config().firebase)

    Database.initialize(admin)

    val express = require("express")
    val app = express()

    // ======================================= SESSION ========================================== \\

    app.post("/v1/session", Api.session::createSession)

    // ======================================= ACCOUNT ========================================== \\

    app.get("/v1/account", Api.account::getAccount)

    app.post("/v1/account", Api.account::createAccount)

    app.patch("/v1/account", Api.account::updateAccount)

    // ======================================== GAMES =========================================== \\

    app.get("/v1/games", Api.game::getOpenGames)

    app.post("/v1/games", Api.game::createGame)

    app.get("/v1/games/:gameId", Api.game::getGame)

    // ======================================== PLAYERS ========================================= \\

    app.post("/v1/games/:gameId/players", Api.game::joinGame)

    app.delete("/v1/games/:gameId/players/:playerId", Api.game::leaveGame)

    app.delete("/v1/games/:gameId/players/:playerId/turn", Api.game::endTurn)

    // ========================================= UNITS ========================================== \\

    app.put("/v1/games/:gameId/cells/:cellId/units/:unitId/move", Api.unit::moveUnits)

    app.put("/v1/games/:gameId/cells/:cellId/units", Api.unit::recruitUnits)

    // ========================================== MAPS ========================================== \\

    app.get("/v1/maps", Api.map::getMaps)

    app.get("/v1/maps/:mapId", Api.map::getMap)

    // ======================================================================================

    val apiExpress = express()
    apiExpress.use("/api", app)
    exports.api = functions.https.onRequest(apiExpress)

    // https://cloud.google.com/nodejs/docs/reference/firestore/0.8.x/DocumentReference

    //exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
    //{
    //	return trigger.onTurnFinished(event)
    //})
}
