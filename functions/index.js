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
const app = express()

// =============================================================================

app.post('/v1/login', (request, response) =>
{
	const sessionId = new shajs.sha512().update(new Date().getTime()).digest('hex')

	return response
				.status(200)
				.set('Session-Token', sessionId)
				.json({foo:"bar"})
})

const api = express()
api.use('/api', app)

exports.api = functions.https.onRequest(api)


//console.log()

//exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
//{
//	return trigger.onTurnFinished(event)
//})