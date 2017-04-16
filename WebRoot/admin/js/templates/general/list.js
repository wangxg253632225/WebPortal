define(['angular'], function(angular) {
	var generalList = angular.module('generalList', []);

	generalList.controller('generalListCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter,$mdDialog) {

		$scope.dataList = new Array();
		$scope.findList = function() {
			$http({
				method: 'POST',
				url: adminUrl + "general/list"
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
		
		$scope.addProject = function(){
			$location.path('/general/add');
		}
		
		//弹出删除选择框
    	$scope.confirmDelete = function(id,name){
		    var confirm = $mdDialog.confirm()
		          .title("项目删除")
		          .textContent("您确定要删除《"+name+"》该项目吗？")
		          .ok('确定')
		          .cancel('取消');
		    $mdDialog.show(confirm).then(function() {
		    	$scope.deleteById(id);
		    }, function() {
		      	$scope.status = 'cancel';
		    });
		};
		
		$scope.deleteById = function(id){
			$http({
				method: 'POST',
				url: adminUrl + "general/delete?id="+id
			})
			.success(function(response) {
				if (response.code == "0") {
					alert = $mdDialog.alert({
				        title: '项目删除',
				        textContent: '项目删除成功',
				        ok: '关闭'
				    });
				    $mdDialog
			        .show( alert )
			        .finally(function() {
			        	$scope.findList();
				    });
				}else{
					$mdDialog.show(
						$mdDialog.alert()
						.title('项目删除')
						.textContent('异常:'+response.msg+"("+response.code+")")
						.ariaLabel('项目删除')
						.ok('关闭')
					);
				}
			})
			.error(function() {
				console.log("shibai");
				return;
			});
		}
		
		$scope.goEdit = function(id){
			$location.url('/general/edit?id='+id);
		}
		

	});
	return generalList;
});