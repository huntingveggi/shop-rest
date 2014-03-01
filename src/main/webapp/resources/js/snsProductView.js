angular.module('snsProductView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/product/:id', {
		controller: 'snsProductCtrl',
		templateUrl: 'resources/partial/product.partial.html'
	});
}])

.controller('snsProductCtrl', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
	$scope.loading = true;
	$http.get('products/' + $routeParams.id).success(function(result) {
		$scope.loading = false;
		
		$scope.image = 'resources/pic/' + result.id + '.png';
		$scope.name = result.name;
		$scope.price = result.price;
		$scope.producer = result.producer.name;
		$scope.description = result.description;
		
		$scope.$parent.breadcrumb = [{
			name: result.categories[0].name,
			path: '/category/' + result.categories[0].id
		}, {
			name: $scope.producer,
			path: '/producer/' + result.producer.id
		}, {
			name: $scope.name,
			path: '/product/' + $routeParams.id
		}];
		console.log($scope.$parent.breadcrumb);
	});
}]);