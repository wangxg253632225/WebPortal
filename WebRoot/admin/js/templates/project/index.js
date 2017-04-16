define(['angular'
		,'js/templates/project/list'
		,'js/templates/project/add'
		,'js/templates/project/edit'], function(angular) {
	var projectIndex = angular.module('projectIndex',['projectList','projectAdd','projectEdit'])
	.config(function($routeProvider) {
		$routeProvider.when('/project/list', {
			templateUrl: 'templates/project/list.html'
		}).when('/project/add', {
			templateUrl: 'templates/project/add.html'
		}).when('/project/edit', {
			templateUrl: 'templates/project/edit.html'
		});
	});
	
	angular._LoadModule('projectIndex');
	return projectIndex;
});