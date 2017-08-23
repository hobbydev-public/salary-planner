package hobbydev.api.web;

import hobbydev.api.models.be.AssetModel;
import hobbydev.api.models.be.EnumModel;
import hobbydev.api.models.be.generic.SuccessModel;
import hobbydev.api.models.fe.AssetView;
import hobbydev.business.exception.ResourceForbiddenOperationException;
import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.business.services.AssetService;
import hobbydev.business.services.UserService;
import hobbydev.config.CurrentUser;
import hobbydev.domain.assets.Asset;
import hobbydev.domain.assets.AssetType;
import hobbydev.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/web/assets")
public class AssetWebRestController {
	
	@Autowired
	private AssetService assetService;
	@Autowired
	private UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<AssetModel>> listUserAssets(@CurrentUser User auth) throws ResourceNotFoundException {
		List<AssetModel> models = assetService.listUserAssets(auth.getId()).stream()
				.map(domain -> new AssetModel(domain))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(models, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "types", method = RequestMethod.GET)
	public ResponseEntity<List<EnumModel>> listTypes() throws ResourceNotFoundException {
		List<EnumModel> models = Arrays.stream(AssetType.values())
				.map(assetType -> new EnumModel(assetType))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(models, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "{assetId}", method = RequestMethod.GET)
	public ResponseEntity<AssetModel> getUserAsset(@PathVariable Long assetId, @CurrentUser User auth) throws ResourceNotFoundException {
		Asset domain = assetService.getUserAsset(assetId, auth.getId());
		AssetModel model = new AssetModel(domain);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<AssetModel> addUserAsset(@RequestBody AssetView view, @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Asset domain = userService.addUserAsset(auth.getId(), view.toDomain());
		return new ResponseEntity<>(new AssetModel(domain), HttpStatus.CREATED);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "{assetId}", method = RequestMethod.DELETE)
	public ResponseEntity<SuccessModel> removeUserAsset(@PathVariable Long assetId, @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		boolean deleted = userService.deleteUserAsset(auth.getId(), assetId);
		return new ResponseEntity<>(new SuccessModel(), deleted? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
}
