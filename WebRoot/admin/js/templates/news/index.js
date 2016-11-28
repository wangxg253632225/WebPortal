define(['angular','js/templates/news/cate_list'], function(angular) {
	var newsIndex = angular.module('newsIndex',['newsCateList'])
	.config(function($routeProvider) {
		$routeProvider.when('/news/cate_list', {
			templateUrl: 'templates/news/cate_list.html'
		});
	});
	
	angular._LoadModule('newsIndex');
	return newsIndex;
});