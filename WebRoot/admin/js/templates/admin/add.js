define(['angular'], function(angular) {
	var adminAdd = angular.module('adminAdd', []);

	adminAdd.controller('adminAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.data = {
			"username": null,
			"password": null
		};

		$scope.addAdmin = function() {
			$http({
				method: 'POST',
				url: adminUrl + "user/addUser",
				data: $scope.data
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