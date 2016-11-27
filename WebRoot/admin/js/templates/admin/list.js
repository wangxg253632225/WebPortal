define(['angular'], function(angular) {
	var adminList = angular.module('adminList', []);

	adminList.controller('adminListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {


		//配置  
		$scope.count = 0;
		$scope.p_pernum = 4;
		//变量  
		$scope.p_current = 1;
		$scope.p_all_page = 0;
		$scope.pages = [];


		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
				method: 'POST',
				url: adminUrl + "user/getList",
				data:{
					"pageNum": $scope.p_current,
					"pageSize": $scope.p_pernum
				}
			})
			.success(function(response) {
				console.log(response);
				if (response.code == "0") {
					$scope.dataList = response.data.list;
					$scope.count = response.data.totalRow;
					$scope.p_all_page = response.data.totalPage;

					reloadPno();
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		};
		
		//上一页
		$scope.Previous = function() {
			if ($scope.p_current == 1) {
				return;
			}
			$scope.p_current = $scope.p_current - 1;
			$scope.findList();
		};
		//下一页
		$scope.Next = function() {
			if ($scope.p_current == $scope.p_all_page) {
				return;
			}
			$scope.p_current = $scope.p_current + 1;
			$scope.findList();
		};

		//加载某一页  
		$scope.load_page = function(page) {
			$scope.p_current = page;
			$scope.findList();
		};

		//初始化页码  
		var reloadPno = function() {
			$scope.pages = calculateIndexes($scope.p_current, $scope.p_all_page, 5);
		};

		//分页算法  
		var calculateIndexes = function(current, length, displayLength) {
			var indexes = [];
			var number = Math.floor(displayLength / 2);
			if(length <= displayLength){
				for (var i = 1; i <= displayLength; i++) {
					indexes.push(i);
				}
			}else{
				if(current - 1 <= number){
					for (var i = 1; i <= displayLength; i++) {
						indexes.push(i);
					}
				}else if(current - 1 > number){
					if(length - current >= number){
						var start= current - number;
						var end = current + number;
						for (var i = start; i <= end; i++) {
							indexes.push(i);
						}
					}else{
						var start= length - displayLength+1;
						for (var i = start; i <= length; i++) {
							indexes.push(i);
						}
					}
				}
			}
//			var start = Math.round(current - displayLength / 2);
//			var end = Math.floor(current + displayLength / 2);
//			if (start <= 1) {
//				start = 1;
//				end = start + displayLength - 1;
//				if (end >= length - 1) {
//					end = length - 1;
//				}
//			}
//			if (end >= length - 1) {
//				end = length;
//				start = end - displayLength+1;
//				if (start <= 1) {
//					start = 1;
//				}
//			}
//			for (var i = start; i <= end; i++) {
//				indexes.push(i);
//			}
			return indexes;
		};
		
		$scope.findList();
		
		$scope.pageSize = 5;
		$scope.pageSizes = [2,5,10,20];
		
		$scope.$watch('pageSize',function(newSize,oldSize,c){
			$scope.p_pernum = newSize;
			$scope.p_current = 1;
			$scope.findList();
		});
	});


	return adminList;
});