import com.mauriciotogneri.momowars.api.Api
import com.mauriciotogneri.momowars.database.Database

external fun require(module: String): dynamic
external val exports: dynamic

fun main(args: Array<String>)
{
    val functions = require("firebase-functions")
    val admin = require("firebase-admin")
    admin.initializeApp(functions.config().firebase)

    Database.firestore = admin.firestore()

    val express = require("express")
    val app = express()
    val api = Api()

    // =================================== SESSION ==========================================

    app.post("/v1/session", api.session::login)

    // ==================================== ACCOUNT =========================================

    app.get("/v1/account", api.account::getAccount)

    app.post("/v1/account", { _, response ->
        // TODO: create account
        response.status(501).send()
    })

    app.patch("/v1/account", { _, response ->
        // TODO: update account
        response.status(501).send()
    })

    // ====================================== GAMES =========================================

    app.get("/v1/games/:gameId", api.game::getGame)

    app.patch("/v1/games/:gameId", { _, response ->
        // TODO: end turn
        response.status(501).send()
    })

    app.post("/v1/games", { _, response ->
        // TODO: create game
        // map_id
        // max_players
        response.status(501).send()
    })

    app.get("/v1/games", { _, response ->
        // TODO: get open game
        response.status(501).send()
    })

    app.post("/v1/games/:gameId", { _, response ->
        // TODO: join game
        response.status(501).send()
    })

    app.delete("/v1/games/:gameId", { _, response ->
        // TODO: leave game
        response.status(501).send()
    })

    // ====================================== UNITS =========================================

    app.patch("/v1/games/:gameId/cells/:cellId/units/:unitId/move", { _, response ->
        // TODO: move units
        // { movement }
        response.status(501).send()
    })

    app.put("/v1/games/:gameId/cells/:cellId/units", { _, response ->
        // TODO: recruit units
        // { type: string, quantity: int }
        response.status(501).send()
    })

    // ======================================= MAPS =========================================

    app.get("/v1/maps", { _, response ->
        // TODO: get maps
        response.status(501).send()
    })

    app.get("/v1/maps/:mapId", { _, response ->
        // TODO: get map
        response.status(501).send()
    })

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
