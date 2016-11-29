define(['angular'], function(angular) {
	var newsEdit = angular.module('newsEdit', []);

	newsEdit.controller('newsEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$routeParams) {
		
		$scope.dataList = new Array();
		$scope.article = {
			id:0,
			cate_id:0,
			name:null,
			title:null,
			content:null,
			author:null
		};
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
		
		/** 加载新闻数据开始  */
		$scope.findDetailById = function(){
			$http({
				method: 'GET',
				url: adminUrl + "article/getDetail?id="+$routeParams.id
			})
			.success(function(response) {
				if(response.code == "0"){
					$scope.article.id = response.data.id;
					$scope.article.cate_id = response.data.cate_id;
					$scope.article.name = response.data.name;
					$scope.article.title = response.data.title;
					$scope.article.author = response.data.author;
					document.getElementById("content").innerHTML = response.data.content;
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
		$scope.findDetailById();
		/** 加载新闻数据结束  */
		/** 更新开始 */
		$scope.updateArticle = function(){
			$scope.article.content = document.getElementById("content").innerHTML;
			
			$http({
				method: 'POST',
				url: adminUrl + "article/update",
				data:$scope.article
			})
			.success(function(response) {
				console.log(response);
				if(response.code == "0"){
					$location.path("/news/news_list");
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		};
		
		/** 更新结束 */
	});
	return newsEdit;
});