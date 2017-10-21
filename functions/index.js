'use strict'

const admin = require('firebase-admin')
admin.initializeApp({
	credential: admin.credential.applicationDefault(),
	databaseURL: 'https://momowars.firebaseio.com',
	storageBucket: 'momowars.appspot.com'
})

const functions = require('firebase-functions')
const database  = new (require('./database/database.js'))(admin)

// =============================================================================

//exports.onTurnFinished = functions.database.ref('games/{gameId}').onUpdate(event =>
//{
//	return trigger.onTurnFinished(event)
//})