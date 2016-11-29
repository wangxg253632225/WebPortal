define(['angular','js/templates/news/cate_list','js/templates/news/news_list'
		,'js/templates/news/news_add'
		,'js/templates/news/news_edit'], function(angular) {
	var newsIndex = angular.module('newsIndex',['newsCateList','newsList','newsAdd','newsEdit'])
	.config(function($routeProvider) {
		$routeProvider.when('/news/cate_list', {
			templateUrl: 'templates/news/cate_list.html'
		}).when('/news/news_list', {
			templateUrl: 'templates/news/news_list.html'
		}).when('/news/news_add', {
			templateUrl: 'templates/news/news_add.html'
		}).when('/news/news_edit', {
			templateUrl: 'templates/news/news_edit.html'
		});
	});
	
	angular._LoadModule('newsIndex');
	return newsIndex;
});