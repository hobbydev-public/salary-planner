export default class AddAssetController {
    constructor($scope,
                assetService,
                currencyService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.assetService = assetService;
        _ctrl.currencyService = currencyService;
        _ctrl.log = logService;
        _ctrl._initData();
    }

    addAsset() {
        let _ctrl = this;

        _ctrl.assetService.addAsset(
            _ctrl.asset,
            function () {
                _ctrl.modalInstance.close(true);
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

        _ctrl.currencies = _ctrl.currencyService.listCurrencies(
            function () {},
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

        _ctrl.types = _ctrl.assetService.listTypes(
            function () {},
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

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }
}