function Database(firestore, document)
{
	this.root = firestore

	this.account = new (require('./database-account.js'))(this, document)
	this.game    = new (require('./database-game.js'))(this, document)
	this.player  = new (require('./database-player.js'))(this, document)
}

module.exports = Database