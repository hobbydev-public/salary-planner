export default class AddCurrencyController {
    constructor($scope,
                currencyService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.currencyService = currencyService;
        _ctrl._initData();
    }

    loadCurrencies(query) {
        let _ctrl = this;
        return _ctrl.currencyService.loadCurrencies(
            query,
            function (httpResp) {
                _ctrl.alerts.push({
                    type: 'danger',
                    title: 'Oh snap!',
                    message: httpResp.data.message
                });

                /*_ctrl.log.error(
                    httpResp.config.method,
                    httpResp.config.url,
                    httpResp.status,
                    httpResp.statusText,
                    httpResp.data.message,
                    httpResp.data.stackTrace
                );*/
            }
        );
    }

    addCurrency() {

    }

    _initData() {
        let _ctrl = this;

        _ctrl.alerts = [];

        _ctrl.currency = null;
    }

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }
}