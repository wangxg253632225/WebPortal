define(['angular'], function(angular) {
	var adminEdit = angular.module('contactEdit', []);

	adminEdit.controller('contactEditCtrl', function($scope, $rootScope, $http, $timeout, $location, $filter, $routeParams,$mdDialog) {

		$scope.data = {
			"id":null,
			"username":null,
			"password":null,
			"remark":"",
			"version":null
		};

		/** 加载新闻数据开始  */
		$scope.findContactDetailById = function() {
			$http({
					method: 'GET',
					url: adminUrl + "contact/getDetail?id=" + $routeParams.id
				})
				.success(function(response) {
					console.log(response);
					if (response.code == "0") {
						$scope.data.id = response.data.id;
						$scope.data.companyName = response.data.company_name;
						$scope.data.businessTime = response.data.business_time;
						$scope.data.mobilePhone = response.data.mobile_phone;
						$scope.data.address = response.data.address;
						$scope.data.addressLat = response.data.address_lat;
						$scope.data.addressLng = response.data.address_lng;
						$scope.data.email = response.data.email;
						$scope.data.fax = response.data.fax;
						$scope.data.landlineTelephone = response.data.landline_telephone;
						$scope.data.content = response.data.content;
						$scope.data.linkMan = response.data.link_man;
						$scope.data.zipCode = response.data.zip_code;
						$scope.data.businessTime = response.data.business_time;
						
						if(response.data.remark != null){
							$scope.data.remark = response.data.remark;
						}
						
					}
				})
				.error(function() {
					console.log("shibai");
					return;
				});
		}
		$scope.findContactDetailById();


		/*更新用户数据*/
		$scope.updateAdmin = function() {
			$http({
					method: 'POST',
					url: adminUrl + "user/updateUser",
					data: $scope.data
				})
				.success(function(response) {
					if (response.code == 0) {
						alert = $mdDialog.alert({
							title: '用户更新',
							textContent: '用户更新成功',
							ok: '关闭'
						});
						$mdDialog
							.show(alert)
							.finally(function() {
								$location.path("/admin/list");
							});
					} else {
						$mdDialog.show(
							$mdDialog.alert()
							.title('用户更新')
							.textContent('异常:' + response.msg + "(" + response.code + ")")
							.ariaLabel('用户更新失败')
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
	return adminEdit;
});