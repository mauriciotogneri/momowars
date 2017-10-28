function Database(admin)
{
	this.root = function()
	{
		return admin.firestore()
	}

	this.games    = new (require('./database-games.js'))(this)
	this.accounts = new (require('./database-accounts.js'))(this)
}

module.exports = Database