<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set var="app" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="en" ng-app="app">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<base href="${app}/">
		<title>App_name</title>
		<link rel="styleSheet" href="res/app/js/node_modules/angular-ui-grid/ui-grid.css"/>
		<link rel="stylesheet" href="res/app/js/node_modules/bootstrap/dist/css/bootstrap.css">
		<link rel="stylesheet" href="res/app/js/node_modules/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="res/app/js/node_modules/admin-lte/dist/css/AdminLTE.css">
		<link rel="stylesheet" href="res/app/js/node_modules/admin-lte/dist/css/skins/_all-skins.min.css">
		<link rel="stylesheet" href="res/app/css/app.css">
	</head>
	
	<body class="skin-blue fixed" ng-controller="rootCtrl">
		<div class="wrapper">
			<header class="main-header">
				<top-nav-bar current-user="appContext.currentUser"></top-nav-bar>
			</header>
			<aside class="main-sidebar">
				<div class="sidebar">
					<ul class="sidebar-menu">
						<li class="header">MAIN NAVIGATION</li>
						<li><a href="#!/dashboard"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>

						<sec:authorize access="hasAnyRole('HR', 'ADMIN')">
							<li class="header">ADMINISTRATION</li>
							<li class="treeview">
								<a href>
									<i class="fa fa-circle-o"></i>
									<span>Node</span>
									<span class="pull-right-container">
										<i class="fa fa-angle-left pull-right"></i>
									</span>
								</a>

								<ul class="treeview-menu">
									<li><a href><i class="fa fa-circle-o"></i> Link 1</a></li>
									<li><a href><i class="fa fa-circle-o"></i> Link 2</a></li>
								</ul>
							</li>
						</sec:authorize>
					</ul>
				</div>
			</aside>
			<div class="content-wrapper">
				<div ng-view></div>
			</div>
			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<a href="docs">API Reference</a>
				</div>

				<!-- Default to the left -->
				App_Name | Year
			</footer>
			<aside class="control-sidebar control-sidebar-dark">

				<!-- TABS -->
				<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
					<li class="active"><a href="#control-sidebar-tab-1" data-toggle="tab"><i class="fa fa-circle-o"></i></a></li>
				</ul>

				<!-- TAB PANES -->
				<div class="tab-content">
					<div class="tab-pane active" id="control-sidebar-tab-1">
						<h4 class="control-sidebar-heading">Tab 1</h4>
						Tab content
					</div>
				</div>
			</aside>
			<div class="control-sidebar-bg"></div>

		</div>



		<script src="res/vendor/datejs/date.js"></script>
		<script src="res/vendor/bignumber/bignumber.js"></script>

		<script src="res/app/js/dist/vendor.bundle.js"></script>
		<script src="res/app/js/node_modules/admin-lte/dist/js/app.js"></script>
		<script src="res/app/js/node_modules/admin-lte/plugins/slimScroll/jquery.slimscroll.js"></script>

		<script src="res/app/js/dist/app.bundle.js"></script>
	</body>
</html>