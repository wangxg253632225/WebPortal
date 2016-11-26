define(['angular','js/templates/admin/list','js/templates/admin/add'], function(angular) {
	var adminIndex = angular.module('adminIndex',['adminList','adminAdd'])
	.config(function($routeProvider) {
		$routeProvider.when('/admin/list', {
			templateUrl: 'templates/admin/list.html'
		}).when('/admin/add', {
			templateUrl: 'templates/admin/add.html'
		});
	});
	
	angular._LoadModule('adminIndex');
	return adminIndex;
});