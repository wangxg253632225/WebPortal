define(['angular'], function(angular) {
	var adminList = angular.module('adminList',[]);
    
//  adminIndex.run(function($http){
//      $http.defaults.headers.common["X-Auth-Token"] = sessionStorage.Token;
//      $http.defaults.headers.common["Content-Type"] = "application/json";
//  });
    
    adminList.controller('adminListCtrl', function($scope, $rootScope,$http,$timeout,$location,$filter) {
    	
    	console.log(adminUrl);
    	
    	$scope.findList = function(){
	    	$http({
				method: 'POST',
				url: adminUrl+"user/getList",
				data: $scope.query
			})
			.success(function(response) {
				console.log(response);
			})
			.error(function() {
				console.log("shibai");
				return;
			});
	    };
	    
	    $scope.findList();
    	
    });
	return adminList;
});