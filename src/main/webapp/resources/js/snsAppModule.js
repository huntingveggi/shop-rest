angular.module('sns', ['ngRoute', 'ngAnimate', 'snsHomeModule', 'snsProductModule'])

.config(['$routeProvider','$locationProvider', function($routeProvider, $locationProvider) {
	$routeProvider.otherwise({
		'redirectTo': '/'
	});
	$locationProvider.html5Mode(false).hashPrefix('!');
}])

.controller('snsAppCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.title = 'Home';
	$scope.titleSeparator = ' | ';
	$scope.titleSuffix = 'Klöpping Elektrotechnik';

	$scope.loggedIn = true;
	$scope.logout = function() { $scope.loggedIn = false; };
	$scope.login = function() { $scope.loggedIn = true; };

	$scope.user = {
		name: 'Dominik Schreiber'
	};

	$scope.breadcrumb = [{
		path: 'category/9',
		name: 'Großgeräte'
	}, {
		path: 'subcategory/5',
		name: 'Kühlschränke'
	}, {
		path: 'item/12345',
		name: 'AEG 123'
	}];

	$scope.categories = [{
		name: 'Waschmaschinen',
		id: 9,
		items: [{
			path: 'subcategory/6',
			name: 'Liebherr',
			count: 203
		}, {
			path: 'subcategory/9',
			name: 'Siemens',
			count: 10
		}, {
			path: 'subcategory/13',
			name: 'Bosch',
			count: 96
		}]
	}, {
		name: 'Geschirrspüler', 
		id: 3,
		items: [{
			path: 'subcategory/1',
			name: 'Liebherr',
			count: 252
		}, {
			path: 'subcategory/5',
			name: 'AEG',
			count: 1
		}, {
			path: 'subcategory/2',
			name: 'Grundig',
			count: 99
		}]
	}, {
		name: 'Kühlschränke',
		id: 2,
		items: [{
			path: 'subcategory/123',
			name: 'Siemens',
			count: 196
		}, {
			path: 'subcategory/12',
			name: 'Mercedes',
			count: 18
		}]
	}];
}]);