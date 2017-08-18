package hobbydev.api.web;

import hobbydev.api.models.be.UserModel;
import hobbydev.api.models.be.generic.SuccessModel;
import hobbydev.api.models.fe.PasswordsView;
import hobbydev.api.models.fe.UserView;
import hobbydev.api.web.exception.HttpBadRequestException;
import hobbydev.business.exception.ResourceForbiddenOperationException;
import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.business.services.UserService;
import hobbydev.config.CurrentUser;
import hobbydev.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/users")
public class UserWebRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<UserModel> addUser(@RequestBody UserView view) throws ResourceForbiddenOperationException {
		UserModel userModel = new UserModel(userService.addUser(view.toDomain()));
		
		return new ResponseEntity<>(userModel, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<UserModel>> listUsers() {
		List<UserModel> userModels = userService.listUsers().stream()
				.map(user -> new UserModel(user))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(userModels, HttpStatus.OK);
	}
	
	@RequestMapping(path = "{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getUserById(@PathVariable Long userId) throws ResourceNotFoundException {
		User user = userService.getUser(userId);
		UserModel userModel = new UserModel(user);
		
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}
	
	@RequestMapping(path = "{userId}", method = RequestMethod.PUT)
	public ResponseEntity<UserModel> updateUser(@PathVariable Long userId,
	                                            @RequestBody UserView view,
	                                            @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		
		if(!userId.equals(view.getId())) {
			throw new HttpBadRequestException("Data is not consistent");
		}
		
		UserModel userModel = new UserModel(userService.updateUser(view.toDomain()));
		
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}
	
	@RequestMapping(path = "{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<SuccessModel> deleteUser(@PathVariable Long userId,
	                                               @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		
		boolean deleted = userService.deleteUser(userId);
		
		return new ResponseEntity<>(new SuccessModel(), deleted? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(path = "password", method = RequestMethod.PUT)
	public ResponseEntity<UserModel> changePassword(@RequestBody PasswordsView passwords,
	                                                @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		User user = userService.changePassword(auth.getId(), passwords.getOldPass(), passwords.getNewPass());
		UserModel userModel = new UserModel(user);
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}
}
