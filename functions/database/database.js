function Database(admin)
{
	this.root = function()
	{
		return admin.firestore()
	}

	this.games = new (require('./database-games.js'))(this)
	this.users = new (require('./database-users.js'))(this)
}

module.exports = Database