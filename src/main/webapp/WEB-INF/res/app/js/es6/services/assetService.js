export default class AssetService {
    constructor($resource,
                $http) {
        'ngInject';

        this.res = $resource(
            'api/web/assets/:assetId/',
            {assetId: '@id'},
            {
                queryTypes: {
                    url: 'api/web/assets/types',
                    method: 'GET',
                    isArray: true
                }
            }
        );
    }

    /**
     * <p>Retrieves asset by its ID.</p>
     *
     * @param assetId - id of a asset
     * @param success - success callback.
     * Success callback is called with the following arguments
     * <ul>
     *     <li>(value (Object|Array)</li>
     *     <li>responseHeaders (Function)</li>
     *     <li>status (number)</li>
     *     <li>statusText (string))</li>
     * </ul>
     * where the value is the populated resource instance or collection object.
     * @param fail - fail callback. Callback is called with (httpResponse) argument.
     */
    getAssetById(assetId, success, fail) {
        let _service = this;

        return _service.res.get(
            {assetId:assetId},
            success,
            fail
        );
    }

    listAssets(success, fail) {
        let _service = this;

        return _service.res.query(
            {},
            success,
            fail
        );
    }

    listTypes(success, fail) {
        let _service = this;

        return _service.res.queryTypes(
            {},
            success,
            fail
        );
    }

    addAsset(assetObject, success, fail) {
        let _service = this;
        let assetResource = new _service.res();

        assetResource.type = assetObject.type;
        assetResource.name = assetObject.name;
        assetResource.balance = assetObject.balance;
        assetResource.currency = assetObject.currency;

        assetResource.$save(
            {},
            success,
            fail
        );
    }
}