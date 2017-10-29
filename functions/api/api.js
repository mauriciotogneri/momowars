function Api(database, model)
{
	this.SESSION_TOKEN = 'Session-Token'

	this.session = new (require('./api-session.js'))(this, database, model)
	this.account = new (require('./api-account.js'))(this, database, model)
	this.game    = new (require('./api-game.js'))(this, database, model)
}

module.exports = Api