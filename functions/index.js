'use strict'

const admin = require('firebase-admin')
admin.initializeApp({
	credential: admin.credential.applicationDefault(),
	databaseURL: 'https://momowars.firebaseio.com',
	storageBucket: 'momowars.appspot.com'
})

const functions = require('firebase-functions')
const express   = require('express')
const shajs     = require('sha.js')
const database  = new (require('./database/database.js'))(admin)
const api       = new (require('./api.js'))(database)
const app = express()

// =============================================================================

app.post('/v1/login', (request, response) =>
{
	return api.login(request, response, shajs)
})

app.get('/v1/games', (request, response) =>
{
	return api.getGames(request, response)
})

app.get('/v1/games/:gameId', (request, response) =>
{
	return api.getGame(request, response)
})

const apiExpress = express()
apiExpress.use('/api', app)
exports.api = functions.https.onRequest(apiExpress)

// https://cloud.google.com/nodejs/docs/reference/firestore/0.8.x/DocumentReference#update

//exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
//{
//	return trigger.onTurnFinished(event)
//})