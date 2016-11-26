define(['angular'], function(angular) {
	var adminAdd = angular.module('adminAdd',[]);
    
    adminAdd.controller('adminAddCtrl', function($scope, $rootScope, $http, $timeout,$location,$filter) {
	    
	    $scope.addAdmin = function(){
	    	$http({
				method: 'POST',
				url: settlementUrl+"account/my/airplan/orderheader/page",
				data: $scope.query
			})
			.success(function(response) {
				console.log(response);
				if(response.code === "0000"){
					$scope.ddHeads = response.data.accounts;
    				$scope.total = response.data.totalRow;
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('新增账单')
						.textContent('异常:' + response.msg + "(" + response.code + ")")
						.ariaLabel('新增账单')
						.ok('关闭')
					);
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
	    };
	    
    });
    
	return adminAdd;
});