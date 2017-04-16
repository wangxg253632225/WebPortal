define(['angular'], function(angular) {
	var projectAdd = angular.module('projectAdd', []);

	projectAdd.controller('projectAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog) {
	
		$scope.param = {
			name:null,
			level:null,
			content:null
		}
	
		$scope.addProject = function(){
			$scope.param.content = document.getElementById("editor1").innerHTML;
			$http({
				method: 'POST',
				url: adminUrl + "project/add",
				data: $scope.param
			})
			.success(function(response) {
				if(response.code == "0"){
					alert = $mdDialog.alert({
				        title: '公司项目新增',
				        textContent: '公司项目新增成功',
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
						.title('公司项目新增')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('公司项目新增')
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
	return projectAdd;
});