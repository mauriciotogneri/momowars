function Database(firestore, models)
{
	this.games    = new (require('./database-games.js'))(firestore, models)
	this.accounts = new (require('./database-accounts.js'))(firestore, models)
}

module.exports = Database