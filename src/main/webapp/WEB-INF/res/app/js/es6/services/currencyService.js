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

    loadCurrencies(query, fail) {
        let _service = this.http;
        let _ctrl = this;

        if(query == 'fail') {
            fail({data:{message: 'Failed!'}});
            return [];
        }

        let tempData = [
            {
                name: 'US Dollars',
                code: 'USD'
            },
            {
                name: 'Canadian Dollars',
                code: 'CAD'
            }
        ];

        return tempData;
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
}