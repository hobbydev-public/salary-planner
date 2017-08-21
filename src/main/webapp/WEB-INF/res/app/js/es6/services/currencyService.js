export default class CurrencyService {
    constructor($resource,
                $http) {
        'ngInject';

        this.http = $http;
        this.res = $resource(
            'api/web/currencies/:currencyId/',
            {currencyId: '@id'},
            {}
        );
    }

    loadCurrencies() {
        let _service = this.http;
        let _ctrl = this;

        return _service({
            method: 'GET',
            url: 'res/app/json/currencies.json'
        });
    }

    /**
     * <p>Retrieves currency by its ID.</p>
     *
     * @param currencyId - id of a currency
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
    getCurrencyById(currencyId, success, fail) {
        let _service = this;

        return _service.res.get(
            {currencyId:currencyId},
            success,
            fail
        );
    }

    listCurrencies(success, fail) {
        let _service = this;

        return _service.res.query(
            {},
            success,
            fail
        );
    }

    addCurrency(currencyCode, success, fail) {
        let _service = this;

        currencyResource.save(
            {
                currencyId: currencyCode
            },
            {},
            success,
            fail
        );
    }
}