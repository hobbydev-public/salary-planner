package hobbydev.api.web;

import hobbydev.api.models.be.UserCurrencyModel;
import hobbydev.api.models.be.generic.SuccessModel;
import hobbydev.api.models.fe.UserCurrencyView;
import hobbydev.business.exception.ResourceForbiddenOperationException;
import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.business.services.UserCurrencyService;
import hobbydev.business.services.UserService;
import hobbydev.config.CurrentUser;
import hobbydev.domain.currencies.UserCurrency;
import hobbydev.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/web/currencies")
public class UserCurrencyWebRestController {
	
	@Autowired
	private UserCurrencyService currencyService;
	@Autowired
	private UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<UserCurrencyModel>> listUserCurrencies(@CurrentUser User auth) throws ResourceNotFoundException {
		List<UserCurrencyModel> models = currencyService.listUserCurrencies(auth.getId()).stream()
				.map(domain -> new UserCurrencyModel(domain))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(models, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "{currencyId}", method = RequestMethod.GET)
	public ResponseEntity<UserCurrencyModel> getUserCurrency(@PathVariable Long currencyId, @CurrentUser User auth) throws ResourceNotFoundException {
		UserCurrency domain = currencyService.getUserCurrency(currencyId, auth.getId());
		UserCurrencyModel model = new UserCurrencyModel(domain);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<UserCurrencyModel> addUserCurrency(@RequestBody UserCurrencyView view, @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		UserCurrency domain = userService.addUserCurrency(auth.getId(), view.toDomain());
		return new ResponseEntity<>(new UserCurrencyModel(domain), HttpStatus.CREATED);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "{currencyId}", method = RequestMethod.DELETE)
	public ResponseEntity<SuccessModel> removeUserCurrency(@PathVariable Long currencyId, @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		boolean deleted = userService.deleteUserCurrency(auth.getId(), currencyId);
		return new ResponseEntity<>(new SuccessModel(), deleted? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
