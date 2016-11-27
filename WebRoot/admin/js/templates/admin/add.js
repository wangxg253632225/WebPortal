define(['angular'], function(angular) {
	var adminAdd = angular.module('adminAdd', []);

	adminAdd.controller('adminAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.data = {
			"username": null,
			"password": null
		};

		$scope.addAdmin = function() {
			console.log($scope.data);
			$http({
					method: 'POST',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					url: adminUrl + "user/addUser",
					data: $.param($scope.data)
				})
				.success(function(response) {

				})
				.error(function() {
					console.log("shibai");
					return;
				});
		};


	});

	return adminAdd;
});