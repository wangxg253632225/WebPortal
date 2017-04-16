define(['angular',
		'js/templates/contact/list',
		'js/templates/contact/add',
		'js/templates/contact/edit'], function(angular) {
	var contactIndex = angular.module('contactIndex',['contactList','contactAdd','contactEdit'])
	.config(function($routeProvider) {
		$routeProvider.when('/contact/list', {
			templateUrl: 'templates/contact/list.html'
		}).when('/contact/add', {
			templateUrl: 'templates/contact/add.html'
		}).when('/contact/edit', {
			templateUrl: 'templates/contact/edit.html'
		});
	});
	
	angular._LoadModule('contactIndex');
	return contactIndex;
});