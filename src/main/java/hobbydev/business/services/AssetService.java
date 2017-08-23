package hobbydev.business.services;

import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.domain.assets.Asset;

import java.util.List;

public interface AssetService {
	
	List<Asset> listUserAssets(Long userId) throws ResourceNotFoundException;
	Asset getUserAsset(Long assetId, Long userId) throws ResourceNotFoundException;
}
