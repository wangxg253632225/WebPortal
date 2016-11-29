define(['angular'], function(angular) {
	var newsAdd = angular.module('newsAdd', []);

	newsAdd.controller('newsAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {
		$scope.dataList = new Array();
		
		/** 新增的请求参数 */
		$scope.param = {
			cate_id:"1",
			name:null,
			title:null,
			author:null,
			content:null
		}
		
		/** 查询出新闻的分类开始 */
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
		/** 查询出新闻的分类结束 */
		
		$scope.addArticle = function(){
			$scope.param.content = document.getElementById("editor1").innerHTML;
			$http({
				method: 'POST',
				url: adminUrl + "article/add",
				data: $scope.param
			})
			.success(function(response) {
				if(response.code == "0"){
					$location.path("/news/news_list");
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
	});
	return newsAdd;
});