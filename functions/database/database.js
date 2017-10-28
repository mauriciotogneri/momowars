function Database(admin, models)
{
	this.root = function()
	{
		return admin.firestore()
	}

	this.games    = new (require('./database-games.js'))(this, models)
	this.accounts = new (require('./database-accounts.js'))(this, models)
}

module.exports = Database