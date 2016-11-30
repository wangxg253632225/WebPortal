define(['angular'], function(angular) {
	var linkList = angular.module('linkList', []);

	linkList.controller('linkListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {

		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
				method: 'POST',
				url: adminUrl + "link/getList"
			})
			.success(function(response) {
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
		
		/** 新增友情链接*/
		$scope.addLink = function(){
			$location.path('/link/link_add');
		}
		
		/** 编辑友情链接*/
		$scope.goEdit = function(id){
			$location.url('/link/link_edit?id='+id);
		}
		/** 删除友情链接*/
		$scope.goDel = function(id){
			$http({
				method: 'GET',
				url: adminUrl + "link/del?id="+id
			})
			.success(function(response) {
				if (response.code == "0") {
					$scope.findList();
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
		

	});
	return linkList;
});