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

app.get('/v1/games', (request, response) =>
{
	const sessionToken = request.get('Session-Token')

	database.users.bySessionToken(sessionToken,
	(user) =>
	{
		console.log(`User found: ${user.email}`)

		response
			.status(200)
			.json(['123'])
	},
	(error) =>
	{
		console.log('User not found')

		response.status(401).send()
	})
})

const api = express()
api.use('/api', app)

exports.api = functions.https.onRequest(api)


//console.log()

//exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
//{
//	return trigger.onTurnFinished(event)
//})