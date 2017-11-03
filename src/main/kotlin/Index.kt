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

    val apiExpress = express()
    apiExpress.use("/api", app)
    exports.api = functions.https.onRequest(apiExpress)

    // https://cloud.google.com/nodejs/docs/reference/firestore/0.8.x/DocumentReference

    //exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
    //{
    //	return trigger.onTurnFinished(event)
    //})
}
