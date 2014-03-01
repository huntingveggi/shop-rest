<!doctype html>

<html ng-app="sns" ng-controller="snsAppCtrl">

<head>
	<title ng-bind="title + titleSeparator + titleSuffix">SNS Consulting</title>

	<meta charset="utf-8">
	<meta name="fragment" content="!">

	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="resources/css/sns.css">
</head>

<body>
	<nav class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sns-navbar-collapse">
							<span class="sr-only">Navigation öffnen</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#!/"><img class="img-responsive" src="resources/img/logo.png" ng-alt="{{titleSuffix}}"></a>
					</div>
					<div class="collapse navbar-collapse" id="sns-navbar-collapse">
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Suche" ng-model="query">
							</div>
							<button type="button" class="btn btn-default" ng-click="search()"><i class="fa fa-search"></i></button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<button class="dropdown-toggle btn btn-default navbar-btn" data-toggle="dropdown">
									Hallo, <span ng-if="!loggedIn">hier anmelden</span><span ng-if="loggedIn">{{user.name}}</span> <b class="caret"></b>
								</button>
								<ul class="dropdown-menu" ng-if="!loggedIn">
									<li><input type="text" ng-model="username" placeholder="Nutzername"></li>
									<li><input type="password" ng-model="password" placeholder="Passwort"></li>
									<li><button type="button" class="btn btn-success btn-block" ng-click="login()">Anmelden</button></li>
									<li><p>Neuer Kunde? <a href="#!/register">Dann hier klicken!</a></p></li>
								</ul>
								<ul class="dropdown-menu" ng-if="loggedIn">
									<li>{{user.name}}</li>
									<li><button type="button" class="btn btn-danger btn-block" ng-click="logout()">Abmelden</button></li>
									<li class="divider"></li>
									<li><a href="#!/account">Mein Konto</a></li>
									<li><a href="#!/orders">Meine Bestellungen</a></li>
									<li><a href="#!/requests">Meine Dienstleistungsanfragen</a></li>
								</ul>
							</li>
							<li ng-if="loggedIn">
								<button class="btn btn-default navbar-btn" href="#!/cart"><a href="#!/cart"><i class="fa fa-shopping-cart fa-lg"></i></a></button>
							</li>
						</ul>
					</div>
			</div>
		</div>
	</nav>	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="#!/">Shop</a></li>
					<li ng-repeat="crumb in breadcrumb" ng-class="{'active': $last}"><span ng-if="$last">{{crumb.name}}</span><a ng-if="!$last" ng-href="#!{{crumb.path}}">{{crumb.name}}</a></li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" ng-repeat="category in categories" ng-init="category.showitems = false">
					<li class="active" ng-click="category.showitems = !category.showitems"><a href="">{{category.name}} <span class="badge pull-right">{{category.items.length}}</span></a></li>
					<li ng-repeat="item in category.items" ng-show="category.showitems"><a ng-href="#!/{{item.path}}">{{item.name}} <span class="badge pull-right">{{item.count}}</span></a></li>
				</ul>
			</div>
			<div class="col-md-10" ng-view></div>
		</div>
	</div>
	<script src="//code.jquery.com/jquery-2.1.0.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.13/angular.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.13/angular-route.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.13/angular-animate.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="resources/js/snsAppModule.js"></script>
	<script src="resources/js/snsHomeModule.js"></script>
	<script src="resources/js/snsProductModule.js"></script>
</body>

</html>