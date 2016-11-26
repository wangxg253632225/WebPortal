define(['angular', 'require', 'ngRoute','cTri','js/config/model'], function(angular, require) {
	
	var app = angular.module('webapp', ['ngRoute','c.tri']);
	var modulelist = [];

	function existModule(moduleName) {
		if(modulelist.indexOf(moduleName) > -1) {
			return true;
		} else {
			modulelist.push(moduleName);
			console.log('load:'+moduleName+'('+modulelist.length+')');
			return false;
		}
	}
	
	angular._LoadModule = function(moduleName) {
		if(!existModule(moduleName)) {
			var m = angular.module(moduleName);
			//console.log('register module:' + moduleName);
			/* 应用的injector，和config中的injector不是同一个，是instanceInject，返回的是通过provider.$get创建的实例 */
			var $injector = angular.element(document).injector();
			/* 递归加载依赖的模块 */
			angular.forEach(m.requires, function(r) {
				angular._LoadModule(r);
			});
			/* 用provider的injector运行模块的controller，directive等等 */
			angular.forEach(m._invokeQueue, function(invokeArgs) {
				try {
					var provider = app.providers.$injector.get(invokeArgs[0]);
					provider[invokeArgs[1]].apply(provider, invokeArgs[2]);
				} catch(e) {
					console.error('load module invokeQueue failed:' + e.message, invokeArgs);
				}
			});
			/* 用provider的injector运行模块的config */
			angular.forEach(m._configBlocks, function(invokeArgs) {
				try {
					app.providers.$injector.invoke.apply(app.providers.$injector, invokeArgs[2]);
				} catch(e) {
					console.error('load module configBlocks failed:' + e.message, invokeArgs);
				}
			});
			/* 用应用的injector运行模块的run */
			angular.forEach(m._runBlocks, function(fn) {
				$injector.invoke(fn);
			});
		}
	};
	
	app.config(['$injector', '$locationProvider', '$routeProvider', '$controllerProvider', '$compileProvider',
		function($injector, $locationProvider, $routeProvider, $controllerProvider, $compileProvider) {
			app.providers = {
				$injector: $injector,
				$controllerProvider: $controllerProvider
			};
			$routeProvider.
			/** 结算中心首页 */
			when('/index', {
				templateUrl: 'templates/index.html',
				resolve: {
					keyName: function($q) {
						var deferred = $q.defer();
						require(['js/templates/index'], function(controller) {
							deferred.resolve();
						});
						return deferred.promise;
					}
				}
			}).
			when('/admin/index', {
					resolve: {
						keyName: function($q) {
							var deferred = $q.defer();
							require(['js/templates/admin/index'], function(controller) {
								deferred.resolve();
							});
							return deferred.promise;
						}
					}
				}).
			otherwise({
				redirectTo: '/index' //angular就喜欢斜杠开头
			});
		}
	]).run(function($rootScope, $location, $http) {
		// 子模块面包屑结束
		$rootScope.go = function(path, title) {
//			if(title) {
//				// 面包屑添加模式开始
//				var i = {
//					path: path,
//					title: title
//				}
//				$rootScope.modCrumb.push(i);
//				publicmodel.breadCrumb = $rootScope.modCrumb;
//			}
//			for(var i = $rootScope.modCrumb.length; i--;) {
//				if(path[path.length - 1] == '/') {
//					path = path.substring(0, path.length - 1);
//				}
//				if($rootScope.modCrumb[i]['path'] == path.toString()) {
//					var len = $rootScope.modCrumb.length;
//					var howMany = len - i + 1;
//					var start = i + 1;
//					$rootScope.modCrumb.splice(start, howMany);
//					publicmodel.breadCrumb = $rootScope.modCrumb;
//					break;
//				}
//			}
			$location.path(path);
		};
		
	}).directive('header', function() {
		return {
			restrict: 'EA',
			templateUrl: 'templates/header.html'
		}
	}).directive('navigation', function() {
		return {
			restrict: 'EA',
			templateUrl: 'templates/navigation.html'
		}
	});
	
	return app;
});