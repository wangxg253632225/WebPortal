define(['angular'], function(angular) {
	var generalAdd = angular.module('generalAdd', []);

	generalAdd.controller('generalAddCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog) {
	
		$scope.param = {
			name:null,
			level:null,
			content:null
		}
	
		$scope.addProject = function(){
			$scope.param.content = document.getElementById("editor1").innerHTML;
			$http({
				method: 'POST',
				url: adminUrl + "general/add",
				data: $scope.param
			})
			.success(function(response) {
				if(response.code == "0"){
					alert = $mdDialog.alert({
				        title: '公司概况新增',
				        textContent: '公司概况新增成功',
				        ok: '关闭'
				    });
				    $mdDialog
			        .show( alert )
			        .finally(function() {
			        	$location.path("/general/list");
				    });
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('公司概况新增')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('公司概况新增')
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
			$location.path('/general/list');
		}
	});
	return generalAdd;
});