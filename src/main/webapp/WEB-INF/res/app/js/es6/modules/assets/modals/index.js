/* Modules */
import angular from 'angular';

/* Modals */
import addAsset from './addAsset';
//import editAsset from './editAsset';

export default angular.module('app.assets.modals', [])
    .component('addAssetModal', addAsset)
    //.component('editAssetModal', editAsset)
    .name