define(['angular'], function(angular) {
	var newsList = angular.module('newsList', []);

	newsList.controller('newsListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
				method: 'POST',
				url: adminUrl + "articleCategory/getList?type=news"
			})
			.success(function(response) {
				console.log(response);
				if (response.code == "0") {
					$scope.dataList = response.data;
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		};
		$scope.findList();
		
		$scope.addArticle = function(){
			$location.path('/news/news_add');
		}

	});
	return newsList;
});