app.controller(CONTROLLER_DASHBOARD, function($scope)
{
	$scope.init = function()
	{
		//firebase.auth().signOut()
		/*firebase.auth().onAuthStateChanged(function(user)
		{
			if (user)
			{
				window.location.href = '/game'
			}
			else
			{
				const form = $('.view-invisible')
				form.removeClass('view-invisible')
				form.addClass('view-visible')
			}
		})*/
	}

	/*$scope.login = function(email, password)
	{
		$scope.form.error = ''
		$scope.form.loading = true

		firebase.auth().signInWithEmailAndPassword(email, password)
		.catch(function(error)
		{
			$scope.form.loading = false
			$scope.$applyAsync()
	
			$scope.form.error = error.message
		})
	}*/

	$scope.init()
})