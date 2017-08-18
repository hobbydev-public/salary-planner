export default class MyProfileCtrl {
    constructor($scope,
                $uibModal,
                principalService,
                userService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;

        _ctrl.userService = userService;
        _ctrl.modalProvider = $uibModal;
        _ctrl.log = logService;

        _ctrl.userId = -1;
        _ctrl.user = null;
        _ctrl.alerts = [];

        principalService.getCurrentUser(function (currentUser) {
            _ctrl.userId = currentUser.id;
            _ctrl._initData();
        });
    }

    openChangePasswordModal() {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'changePasswordModal',
        });

        modal.result.then(
            function (success) {
                // on close
                if(success) window.location.reload();
            },
            function () {
                // on dismiss
            }
        );
    }

    _initData() {
        let _ctrl = this;

        _ctrl.user = _ctrl.userService.getUserById(
            _ctrl.userId,
            function (user) {
                
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

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }
}