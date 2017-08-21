import currenciesListCtrl from './controllers/currenciesListCtrl';

export default function routing($routeProvider) {
    'ngInject';

    $routeProvider
        .when('/currencies', {
            template: require('./templates/currenciesList.html'),
            controller: currenciesListCtrl,
            controllerAs: '$ctrl',
        });
}