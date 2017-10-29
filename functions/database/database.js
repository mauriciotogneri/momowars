function Database(firestore, models)
{
	this.accounts = new (require('./database-accounts.js'))(firestore, models)
	this.games    = new (require('./database-games.js'))(firestore, models)
	this.players  = new (require('./database-players.js'))(firestore, models)
}

module.exports = Database