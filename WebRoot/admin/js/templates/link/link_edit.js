define(['angular'], function(angular) {
	var linkEdit = angular.module('linkEdit', []);

	linkEdit.controller('linkEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter, $routeParams) {

		$scope.data = {
			"id":0,
			"linkName": null,
			"linkUrl": null,
			"level": null,
			"remark": null,
			"version":null,
		};

		/** 加载新闻数据开始  */
		$scope.findCateDetailById = function() {
			$http({
					method: 'GET',
					url: adminUrl + "link/getDetail?id=" + $routeParams.id
				})
				.success(function(response) {
					console.log(response);
					if (response.code == "0") {
						$scope.data.id = response.data.id;
						$scope.data.linkName = response.data.link_name;
						$scope.data.linkUrl = response.data.link_url;
						$scope.data.level = response.data.level;
						$scope.data.remark = response.data.remark;
						$scope.data.version = response.data.version;
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		}
		$scope.findCateDetailById();


		/*更新分类数据*/
		$scope.updateLink = function() {
			$http({
					method: 'POST',
					url: adminUrl + "link/updateLink",
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
	return linkEdit;
});