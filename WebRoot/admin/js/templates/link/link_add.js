define(['angular'], function(angular) {
	var linkAdd = angular.module('linkAdd', []);

	linkAdd.controller('linkAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.data = {
			"linkName": null,
			"linkUrl": null,
			"level": null,
			"remark": null
		};
		/*新增分类*/
		$scope.addLink = function() {
			console.log($scope.data);
			$http({
					method: 'POST',
					url: adminUrl + "link/addLink",
					data: $scope.data
				})
				.success(function(response) {
					if (response.code == 0) {
						$location.url("/link/link_list");
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		};
	});
	return linkAdd;
});