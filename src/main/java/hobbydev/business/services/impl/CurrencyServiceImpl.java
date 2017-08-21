package hobbydev.business.services.impl;

import hobbydev.business.AbstractService;
import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.business.services.CurrencyService;
import hobbydev.domain.currencies.UserCurrency;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl extends AbstractService implements CurrencyService {
	
	@Override
	protected Class<UserCurrency> getEntityClass() {
		return UserCurrency.class;
	}
	
	@Override
	@Transactional
	public List<UserCurrency> listUserCurrencies(Long userId) throws ResourceNotFoundException {
		if(userId == null || Long.valueOf(0).compareTo(userId) >= 0) {
			throw new ResourceNotFoundException("User ID does not exist.");
		}
		
		List<UserCurrency> currencies = getDAO().getAll(getEntityClass()).stream()
				.filter(currency -> currency.getUser().getId().equals(userId))
				.collect(Collectors.toList());
		return currencies;
	}
	
	@Override
	@Transactional
	public UserCurrency getUserCurrency(Long currencyId, Long userId) throws ResourceNotFoundException {
		if(userId == null || Long.valueOf(0).compareTo(userId) >= 0) {
			throw new ResourceNotFoundException("User ID does not exist.");
		}
		
		if(currencyId == null || Long.valueOf(0).compareTo(currencyId) >= 0) {
			throw new ResourceNotFoundException("Currency ID does not exist.");
		}
		
		UserCurrency currency = listUserCurrencies(userId).stream()
				.filter(c -> c.getId().equals(currencyId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("User with ID=[" + userId + "] does not have a currency with ID=[" + currencyId + "]."));
		
		return currency;
	}
}
