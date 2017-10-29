function Api(database)
{
	this.SESSION_TOKEN = 'Session-Token'

	this.session = new (require('./api-session.js'))(this, database)
	this.account = new (require('./api-account.js'))(this, database)
	this.game    = new (require('./api-game.js'))(this, database)
}

module.exports = Api