<!doctype html>

<html ng-app>

<head>
<title>SNS Consulting</title>
<meta charset="utf-8">
<meta name="fragment" content="!">

</head>

<body ng-controller="basic">

	<div style="font-weight: bold; font-size: 20px;">ShoppingCart</div>
	<br>
	<div>
		<a href="{{baseUrl}}/shopping-cart/">{{baseUrl}}/shopping-cart/</a>
		<div>Anzeigen des Warenkorbs</div>

		<br> <a href="{{baseUrl}}/shopping-cart/add/1">{{baseUrl}}/shopping-cart/add/1</a><br>
		<div>Hinzufügen eines Artikels mit der id 1</div>
		
		<br> <a href="{{baseUrl}}/shopping-cart/remove/1">{{baseUrl}}/shopping-cart/remove/1</a><br>
		<div>Entfernen eines Artikels mit der id 1</div>
		
		<br> <a href="{{baseUrl}}/shopping-cart/reset">{{baseUrl}}/shopping-cart/reset</a><br>
		<div>Reset des Warenkorbs</div>
		
		<br> <a href="{{baseUrl}}/shopping-cart/create-order">{{baseUrl}}/shopping-cart/create-order</a><br>
		<div>Anlegen einer Bestellung (Nicht Bestellung ausführen)</div>
		
		<br> <a href="{{baseUrl}}/shopping-cart/refresh">{{baseUrl}}/shopping-cart/refresh</a><br>
		<div>Alle Daten im Warenkorb werden aktualisiert</div>
	</div>
	
	<hr>
	
	<div style="font-weight: bold; font-size: 20px;">Produkte</div>
	<br>
	<div>
		<a href="{{baseUrl}}/products/current-offers">{{baseUrl}}/products/current-offers</a>
		<div>Liefert eine Liste der aktuellen "Spezialangebote"</div>

		<br> <a href="{{baseUrl}}/products/1">{{baseUrl}}/products/1</a><br>
		<div>Liefert das Produkt mit der id 1</div>
		
	</div>


	<script
		src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.13/angular.min.js"></script>

	<script type="text/javascript">
		function basic($scope) {
			$scope.baseUrl = 'http://localhost:8080/shop';
		}
	</script>
</body>

</html>