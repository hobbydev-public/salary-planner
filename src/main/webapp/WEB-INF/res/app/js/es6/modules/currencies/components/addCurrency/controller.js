export default class AddCurrencyController {
    constructor($scope,
                $http,
                currencyService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.currencyService = currencyService;
        _ctrl.httpService = $http;
        _ctrl._initData();
    }

    loadCurrencies(query) {

    }

    _initData() {
        let _ctrl = this;
    }
}