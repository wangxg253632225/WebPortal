define(['angular'
		,'js/templates/general/list'
		,'js/templates/general/add'
		,'js/templates/general/edit'], function(angular) {
	var generalIndex = angular.module('generalIndex',['generalList','generalAdd','generalEdit'])
	.config(function($routeProvider) {
		$routeProvider.when('/general/list', {
			templateUrl: 'templates/general/list.html'
		}).when('/general/add', {
			templateUrl: 'templates/general/add.html'
		}).when('/general/edit', {
			templateUrl: 'templates/general/edit.html'
		});
	});
	
	angular._LoadModule('generalIndex');
	return generalIndex;
});