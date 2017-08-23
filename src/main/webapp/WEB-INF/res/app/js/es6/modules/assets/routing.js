import assetsListCtrl from './controllers/assetsListCtrl';

export default function routing($routeProvider) {
    'ngInject';

    $routeProvider
        .when('/assets', {
            template: require('./templates/assetsList.html'),
            controller: assetsListCtrl,
            controllerAs: '$ctrl',
        })/*
        .when('/assets/:assetId', {
            template: require('./templates/assetView.html'),
            controller: assetViewCtrl,
            controllerAs: '$ctrl',
        })*/;
}