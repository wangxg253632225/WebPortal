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
					console.log(response.data);
					$scope.dataList = response.data;
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		};
		$scope.findList();
		
		$scope.addLink = function(){
			$location.path('/link/link_add');
		}
		
		/*编辑分类*/
		$scope.goEdit = function(id){
			console.log(id);
			$location.url('/link/link_edit?id='+id);
		}

	});
	return linkList;
});