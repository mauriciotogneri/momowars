function Database(firestore, models)
{
	this.root = firestore

	this.accounts = new (require('./database-accounts.js'))(this, models)
	this.games    = new (require('./database-games.js'))(this, models)
	this.players  = new (require('./database-players.js'))(this, models)
}

module.exports = Database