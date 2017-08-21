/* Modules */
import angular from 'angular';

/* Components */
import addCurrency from './addCurrency';

export default angular.module('app.currencies.components', [])
    .component('addCurrencyForm', addCurrency)
    .name