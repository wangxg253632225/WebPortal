define(['angular'], function(angular) {
	var adminList = angular.module('adminList', []);

	//  adminIndex.run(function($http){
	//      $http.defaults.headers.common["X-Auth-Token"] = sessionStorage.Token;
	//      $http.defaults.headers.common["Content-Type"] = "application/json";
	//  });

	adminList.controller('adminListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter) {


		//配置  
		$scope.count = 0;
		$scope.p_pernum = 2;
		//变量  
		$scope.p_current = 1;
		$scope.p_all_page = 0;
		$scope.pages = [];


		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
					method: 'POST',
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					url: adminUrl + "user/getList",
					data: $.param({
						"pageNum": $scope.p_current,
						"pageSize": $scope.p_pernum
					})
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

		//首页  
		$scope.p_index = function() {
			$scope.load_page(1);
		}

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

		//尾页  
		$scope.p_last = function() {
			$scope.load_page($scope.p_all_page);
		}

		//加载某一页  
		$scope.load_page = function(page) {
			$scope.p_current = page;
			$scope.findList();
		};

		//初始化页码  
		var reloadPno = function() {
			console.log($scope.p_current);
			console.log($scope.p_all_page);
			$scope.pages = calculateIndexes($scope.p_current, $scope.p_all_page, 3);
		};

		//分页算法  
		var calculateIndexes = function(current, length, displayLength) {
			var indexes = [];
			var start = Math.round(current - displayLength / 2);
			var end = Math.round(current + displayLength / 2);
			if (start <= 1) {
				start = 1;
				end = start + displayLength - 1;
				if (end >= length - 1) {
					end = length - 1;
				}
			}
			if (end >= length - 1) {
				end = length;
				start = end - displayLength + 1;
				if (start <= 1) {
					start = 1;
				}
			}
			for (var i = start; i <= end; i++) {
				indexes.push(i);
			}
			return indexes;
		};




		$scope.findList();

	});


	return adminList;
});