function Database(admin)
{
	this.ref = function(path)
	{
		return admin.database().ref(path)
	}

	this.root = function(callback)
	{
		return this.ref('/').once('value', snap =>
		{
			callback(snap.val())
		})
	}

	this.games = new (require('./database-games.js'))(this)
}

module.exports = Database