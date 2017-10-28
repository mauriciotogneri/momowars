'use strict'

const functions = require('firebase-functions')
const admin = require('firebase-admin').initializeApp(functions.config().firebase)

const express   = require('express')
const shajs     = require('sha.js')
const models    = require('./models/models.js')
const database  = new (require('./database/database.js'))(admin, models)
const api       = new (require('./api/api.js'))(database)
const app = express()

// =============================================================================

app.post('/v1/login', (request, response) =>
{
	return api.sessions.login(request, response, shajs)
})

app.get('/v1/account', (request, response) =>
{
	return api.accounts.getAccount(request, response)
})

app.get('/v1/games/:gameId', (request, response) =>
{
	return api.games.getGame(request, response)
})

const apiExpress = express()
apiExpress.use('/api', app)
exports.api = functions.https.onRequest(apiExpress)

// https://cloud.google.com/nodejs/docs/reference/firestore/0.8.x/DocumentReference#update

//exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
//{
//	return trigger.onTurnFinished(event)
//})