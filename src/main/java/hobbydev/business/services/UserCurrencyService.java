package hobbydev.business.services;

import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.domain.currencies.UserCurrency;

import java.util.List;

public interface UserCurrencyService {
    
    List<UserCurrency> listUserCurrencies(Long userId) throws ResourceNotFoundException;
    UserCurrency getUserCurrency(Long currencyId, Long userId)  throws ResourceNotFoundException;
}
