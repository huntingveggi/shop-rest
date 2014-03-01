angular.module('snsHomeView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		controller: 'snsHomeCtrl',
		templateUrl: 'resources/partial/home.partial.html'
	});
}])

.controller('snsHomeCtrl', ['$scope', '$http', function($scope, $http) {

}]);