'use strict'

const functions = require('firebase-functions')
const admin     = require('firebase-admin').initializeApp(functions.config().firebase)
const express   = require('express')
const shajs     = require('sha.js')
const models    = require('./models/models.js')
const database  = new (require('./database/database.js'))(admin, models)
const api       = new (require('./api/api.js'))(database)
const app       = express()

// =================================== SESSION ==========================================

app.post('/v1/session', (request, response) =>
{
	return api.sessions.login(request, response, shajs)
})

// ==================================== ACCOUNT =========================================

app.get('/v1/account', (request, response) =>
{
	return api.accounts.getAccount(request, response)
})

app.post('/v1/account', (request, response) =>
{
	// TODO: create account
	response.status(501).send()
})

app.patch('/v1/account', (request, response) =>
{
	// TODO: update account
	response.status(501).send()
})

// ====================================== GAMES =========================================

app.get('/v1/games/:gameId', (request, response) =>
{
	return api.games.getGame(request, response)
})

app.post('/v1/games', (request, response) =>
{
	// TODO: create game
	response.status(501).send()
})

app.post('/v1/games/:gameId', (request, response) =>
{
	// TODO: join game
	response.status(501).send()
})

// ======================================================================================

const apiExpress = express()
apiExpress.use('/api', app)
exports.api = functions.https.onRequest(apiExpress)

// https://cloud.google.com/nodejs/docs/reference/firestore/0.8.x/DocumentReference#update

//exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
//{
//	return trigger.onTurnFinished(event)
//})