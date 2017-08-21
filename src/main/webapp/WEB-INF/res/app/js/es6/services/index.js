/* Modules */
import angular from 'angular';
import resource from 'angular-resource';

/* Services */
import principalService from './principalService';
import userService from './userService';
import logService from './logService';
import currencyService from './currencyService';

export default angular.module('app.services',
    [
        resource
    ])
    .service('principalService', principalService)
    .service('userService', userService)
    .service('logService', logService)
    .service('currencyService', currencyService)
    .name;