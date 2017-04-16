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
			}]
		},{
			name: '企业概况',
			path: '/general/index',
			icon: 'icon-desktop',
			children: [{
				name: '概况管理',
				path: '/general/list',
				icon: 'icon-desktop'
			}]
		},{
			name: '公司项目',
			path: '/project/index',
			icon: 'icon-desktop',
			children: [{
				name: '项目维护',
				path: '/project/list',
				icon: 'icon-desktop'
			}]
		},{
			name: '新闻中心',
			path: '/news/index',
			icon: 'icon-desktop',
			children: [{
				name: '新闻分类',
				path: '/news/cate_list',
				icon: 'icon-desktop'
			},{
				name: '新闻管理',
				path: '/news/news_list',
				icon: 'icon-desktop'
			}]
		},{
			name: '人力资源',
			path: '/news/index',
			icon: 'icon-desktop',
			children: [{
				name: '人力资源分类',
				path: '/news/cate_list',
				icon: 'icon-desktop'
			},{
				name: '人力资源管理',
				path: '/news/news_list',
				icon: 'icon-desktop'
			}]
		},{
			name: '联系我们',
			path: '/contact/index',
			icon: 'icon-desktop',
			children: [{
				name: '信息维护',
				path: '/contact/list',
				icon: 'icon-desktop'
			}]
		},{
			name: '友情链接',
			path: '/link/index',
			icon: 'icon-desktop',
			children: [{
				name: '链接管理',
				path: '/link/link_list',
				icon: 'icon-desktop'
			}]
		}];

		$scope.onOpenMenuOrHref = function(item) {
			if(item.path) {
				$location.url(item.path);
			}
		}
		
		//跳转
		$scope.getUrl = function(url){
			$location.path(url);
		}
	}])
});