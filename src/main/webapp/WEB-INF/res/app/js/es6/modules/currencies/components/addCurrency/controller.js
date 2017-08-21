export default class AddCurrencyController {
    constructor($scope,
                currencyService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.currencyService = currencyService;
        _ctrl.log = logService;
        _ctrl._initData();
    }

    loadCurrencies(query) {
        let _ctrl = this;
        return _ctrl.currencyService.loadCurrencies().then(
            function(dataArray){
                return dataArray.data.filter(function (item) {
                    if(item.name.toLowerCase().indexOf(query.toLowerCase()) !== -1 || item.code.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
                        return true;
                    }
                    return false;
                }, _ctrl);
            },
            function (httpResp) {
                _ctrl.alerts.push({
                    type: 'danger',
                    title: 'Oh snap!',
                    message: httpResp.data.message
                });

                _ctrl.log.error(
                    httpResp.config.method,
                    httpResp.config.url,
                    httpResp.status,
                    httpResp.statusText,
                    httpResp.data.message,
                    httpResp.data.stackTrace
                );
            });
    }

    addCurrency() {
        let _ctrl = this;

        _ctrl.currencyService.addCurrency(
            _ctrl.currency,
            function () {
                window.location.reload();
            },
            function (httpResp) {
                _ctrl.alerts.push({
                    type: 'danger',
                    title: 'Oh snap!',
                    message: httpResp.data.message
                });

                _ctrl.log.error(
                    httpResp.config.method,
                    httpResp.config.url,
                    httpResp.status,
                    httpResp.statusText,
                    httpResp.data.message,
                    httpResp.data.stackTrace
                );
            }
        );
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