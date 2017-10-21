app.controller(CONTROLLER_GAME, function($scope, database, databaseGame, ui)
{
	$scope.loading = true

	$scope.init = function()
	{
		// TODO
		$scope.loading = false
		$scope.$applyAsync()
	}

	$scope.init()
})