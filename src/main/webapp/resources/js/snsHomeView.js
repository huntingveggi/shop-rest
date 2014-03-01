angular.module('snsHomeView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		controller: 'snsHomeCtrl',
		templateUrl: 'resources/partial/home.partial.html'
	});
}])

.controller('snsHomeCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.loading = true;
	$scope.$parent.breadcrumb = [{
		name: 'Angebote', 
		path: '/'
	}];
	$scope.items = [];
	
	$http.get('products/current-offers.json').success(function(result) {
		$scope.loading = false;
		
		
		$scope.items = result.map(function(item) {
			return {
				image: 'resources/pic/' + item.id + '.png',
				name: item.name,
				price: item.price,
				producer: (item.producer) ? item.producer.name : undefined,
				description: item.description,
				id: item.id
			};
		});
	}).error(function(result) {
		console.warn(result);
	});
}]);