'use strict'

const functions = require('firebase-functions')
const admin     = require('firebase-admin').initializeApp(functions.config().firebase)
const express   = require('express')
const document  = require('./document/document.js')
const model     = require('./model/model.js')
const constants = require('./constants.js')
const database  = new (require('./database/database.js'))(admin.firestore(), document)
const api       = new (require('./api/api.js'))(database, model)
const app       = express()

// =================================== SESSION ==========================================

app.post('/v1/session', api.session.login)

// ==================================== ACCOUNT =========================================

app.get('/v1/account', api.account.getAccount)

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

app.get('/v1/games/:gameId', api.game.getGame)

app.patch('/v1/games/:gameId', (request, response) =>
{
	// TODO: end turn
	response.status(501).send()
})

app.post('/v1/games', (request, response) =>
{
	// TODO: create game
	// map_id
	// max_players
	response.status(501).send()
})

app.get('/v1/games', (request, response) =>
{
	// TODO: get open game
	response.status(501).send()
})

app.post('/v1/games/:gameId', (request, response) =>
{
	// TODO: join game
	response.status(501).send()
})

app.delete('/v1/games/:gameId', (request, response) =>
{
	// TODO: leave game
	response.status(501).send()
})

// ====================================== UNITS =========================================

app.patch('/v1/games/:gameId/cells/:cellId/units/:unitId/move', (request, response) =>
{
	// TODO: move units
	// { movement }
	response.status(501).send()
})

app.put('/v1/games/:gameId/cells/:cellId/units', (request, response) =>
{
	// TODO: recruit units
	// { type: string, quantity: int }
	response.status(501).send()
})

// ======================================= MAPS =========================================

app.get('/v1/maps', (request, response) =>
{
	// TODO: get maps
	response.status(501).send()
})

app.get('/v1/maps/:mapId', (request, response) =>
{
	// TODO: get map
	response.status(501).send()
})

// ======================================================================================

const apiExpress = express()
apiExpress.use('/api', app)
exports.api = functions.https.onRequest(apiExpress)

// https://cloud.google.com/nodejs/docs/reference/firestore/0.8.x/DocumentReference

//exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
//{
//	return trigger.onTurnFinished(event)
//})