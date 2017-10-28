function Api(database)
{
	this.sessions = new (require('./api-sessions.js'))(database)
	this.accounts = new (require('./api-accounts.js'))(database)
	this.games    = new (require('./api-games.js'))(database)
}

module.exports = Api