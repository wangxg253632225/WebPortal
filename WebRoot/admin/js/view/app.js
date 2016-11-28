console.log("进入view.app。。。");

define(['angular'], function(angular) {
	angular.module('c.tri',[]).controller("AppCtrl", ["$rootScope", "$scope", "$timeout", '$location', '$http', function($rs, $scope, $timeout, $location,$http) {

		$scope.items = [{
			name: '管理员管理',
			path: '/admin/index',
			icon: 'icon-desktop',
			children: [{
				name: '管理员列表',
				path: '/admin/list',
				icon: 'icon-double-angle-right'
			},{
				name: '新增管理员',
				path: '/admin/add',
				icon: 'icon-double-angle-right'
			}]
		},{
			name: '新闻中心',
			path: '/news/index',
			icon: 'icon-desktop',
			children: [{
				name: '新闻分类',
				path: '/news/cate_list',
				icon: 'icon-desktop',
			},{
				name: '新闻管理',
				path: '',
				icon: 'icon-desktop'
			}]
		}];

		$scope.onOpenMenuOrHref = function(item) {
			if(item.path) {
				$location.url(item.path);
			}
		}
	}])
});