export default class CurrenciesListController {
    constructor($scope,
                $uibModal,
                currencyService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.currencyService = currencyService;
        _ctrl.modalProvider = $uibModal;
        _ctrl.log = logService;

        this._initData();

    }

    openRemoveCurrencyModal(currencyId) {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'deleteConfirmationModal',
            resolve: {
                message: function () {
                    return 'You are about to delete the following currency:';
                },
                resource: function () {
                    return _ctrl.currencyService.getCurrencyById(
                        currencyId,
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
            }
        });

        modal.result.then(
            function (success) {
                // on close
                if(success) {
                    window.location.reload();
                }
            },
            function () {
                // on dismiss
            }
        );
    }

    _initData() {
        let _ctrl = this;
        _ctrl.alerts = [];

        _ctrl.currencies = _ctrl.currencyService.getCurrencies(
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