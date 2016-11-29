define(['angular'], function(angular) {
	var newsList = angular.module('newsList', []);

	newsList.controller('newsListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
				method: 'POST',
				url: adminUrl + "article/getList?type=news",
				data:{
					pageNum:1,
					pageSize:10
				}
			})
			.success(function(response) {
				console.log(response);
				if (response.code == "0") {
					$scope.dataList = response.data.list;
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
		
		$scope.goEdit = function(id){
			$location.url('/news/news_edit?id='+id);
		}

	});
	return newsList;
});