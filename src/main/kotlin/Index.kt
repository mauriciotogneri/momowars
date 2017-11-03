import api.Api

external fun require(module: String): dynamic
external val exports: dynamic

fun main(args: Array<String>) {
    val functions = require("firebase-functions")
    val admin = require("firebase-admin")
    admin.initializeApp(functions.config().firebase)

    val express = require("express")
    val app = express()

    /*app.get("/", { req, res ->
        res.type("text/plain")
        res.send("i am a beautiful butterfly")
    })

    app.listen(3000, {
        println("Listening on port 3000")
    })

    exports.saveString = functions.https.onRequest { req, res ->
        val text = req.query.text

        res.status(200).send("Saved: $text")
    }*/

    val api = Api()

    // =================================== SESSION ==========================================

    app.post("/v1/session", api.session::login)

    // ==================================== ACCOUNT =========================================

    app.get("/v1/account", api.account::getAccount)

    app.post("/v1/account", { request, response ->
        // TODO: create account
        response.status(501).send()
    })

    app.patch("/v1/account", { request, response ->
        // TODO: update account
        response.status(501).send()
    })

    // ====================================== GAMES =========================================

    app.get("/v1/games/:gameId", api.game::getGame)

    app.patch("/v1/games/:gameId", { request, response ->
        // TODO: end turn
        response.status(501).send()
    })

    app.post("/v1/games", { request, response ->
        // TODO: create game
        // map_id
        // max_players
        response.status(501).send()
    })

    app.get("/v1/games", { request, response ->
        // TODO: get open game
        response.status(501).send()
    })

    app.post("/v1/games/:gameId", { request, response ->
        // TODO: join game
        response.status(501).send()
    })

    app.delete("/v1/games/:gameId", { request, response ->
        // TODO: leave game
        response.status(501).send()
    })

    // ====================================== UNITS =========================================

    app.patch("/v1/games/:gameId/cells/:cellId/units/:unitId/move", { request, response ->
        // TODO: move units
        // { movement }
        response.status(501).send()
    })

    app.put("/v1/games/:gameId/cells/:cellId/units", { request, response ->
        // TODO: recruit units
        // { type: string, quantity: int }
        response.status(501).send()
    })

    // ======================================= MAPS =========================================

    app.get("/v1/maps", { request, response ->
        // TODO: get maps
        response.status(501).send()
    })

    app.get("/v1/maps/:mapId", { request, response ->
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
