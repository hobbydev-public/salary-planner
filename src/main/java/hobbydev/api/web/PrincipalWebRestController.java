package hobbydev.api.web;

import hobbydev.api.models.be.AnonymousPrincipalModel;
import hobbydev.api.models.be.PrincipalModel;
import hobbydev.config.CurrentUser;
import hobbydev.domain.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/web/principal")
public class PrincipalWebRestController {
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<PrincipalModel> getPrincipal(@CurrentUser User user){
		PrincipalModel principalModel = (user == null)? new AnonymousPrincipalModel(): new PrincipalModel(user);
		
		return new ResponseEntity<>(principalModel, HttpStatus.OK);
	}
}
