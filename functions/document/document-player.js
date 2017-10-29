function DocumentPlayer(playerDoc, accountDoc)
{
	const data = playerDoc.data()

	this.nickname = accountDoc.data().nickname
	this.color    = playerDoc.color
	this.status   = playerDoc.status
}

module.exports = DocumentPlayer