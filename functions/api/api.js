function Api(database)
{
	this.SESSION_TOKEN = 'Session-Token'

	this.sessions = new (require('./api-sessions.js'))(this, database)
	this.accounts = new (require('./api-accounts.js'))(this, database)
	this.games    = new (require('./api-games.js'))(this, database)
}

module.exports = Api