function GameDoc(doc)
{
	const data = doc.data()

	this.json = function()
	{
		return {
			map: data.map,
			status: data.status
		}
	}
}

module.exports = GameDoc