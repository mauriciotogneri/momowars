function Database(firestore, documents)
{
	this.root = firestore

	this.accounts = new (require('./database-accounts.js'))(this, documents)
	this.games    = new (require('./database-games.js'))(this, documents)
	this.players  = new (require('./database-players.js'))(this, documents)
}

module.exports = Database