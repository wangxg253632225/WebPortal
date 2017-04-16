define(['angular'], function(angular) {
	var projectEdit = angular.module('projectEdit', []);

	projectEdit.controller('projectEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog,$routeParams) {
	
		$scope.param = {
			name:null,
			level:null,
			content:null
		}
		
		$scope.findDetail = function(){
			$http({
				method: 'POST',
				url: adminUrl + "project/detail?id="+$routeParams.id
			})
			.success(function(response) {
				if(response.code == "0"){
					$scope.param = response.data;
					document.getElementById("editor1").innerHTML = response.data.content;
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('获取详情')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('获取详情')
						.ok('关闭')
					);
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
		$scope.findDetail();
	
		$scope.addProject = function(){
			$scope.param.content = document.getElementById("editor1").innerHTML;
			$http({
				method: 'POST',
				url: adminUrl + "project/update",
				data: $scope.param
			})
			.success(function(response) {
				if(response.code == "0"){
					alert = $mdDialog.alert({
				        title: '公司项目编辑',
				        textContent: '公司项目编辑成功',
				        ok: '关闭'
				    });
				    $mdDialog
			        .show( alert )
			        .finally(function() {
			        	$location.path("/project/list");
				    });
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('公司项目编辑')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('公司项目编辑')
						.ok('关闭')
					);
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
		
		$scope.goList = function(){
			$location.path('/project/list');
		}
	});
	return projectEdit;
});