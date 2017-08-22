export default class AssetsListController {
    constructor($scope,
                $uibModal,
                assetService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.assetService = assetService;
        _ctrl.modalProvider = $uibModal;
        _ctrl.log = logService;

        this._initData();

    }

    openAddAssetModal() {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'addAssetModal'
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

    openRemoveAssetModal(assetId) {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'deleteConfirmationModal',
            resolve: {
                message: function () {
                    return 'You are about to delete the following asset:';
                },
                resource: function () {
                    return _ctrl.assetService.getAssetById(
                        assetId,
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

        _ctrl.assets = _ctrl.assetService.listAssets(
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