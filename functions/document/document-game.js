function DocumentGame(doc)
{
	const data = doc.data()

	this.id     = doc.id
	this.status = data.status
	this.map    = data.map
}

module.exports = DocumentGame