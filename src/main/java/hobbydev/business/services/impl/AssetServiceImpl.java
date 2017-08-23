package hobbydev.business.services.impl;

import hobbydev.business.AbstractService;
import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.business.services.AssetService;
import hobbydev.domain.assets.Asset;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl extends AbstractService implements AssetService {
	
	@Override
	protected Class<Asset> getEntityClass() {
		return Asset.class;
	}
	
	@Override
	@Transactional
	public List<Asset> listUserAssets(Long userId) throws ResourceNotFoundException {
		if(userId == null || Long.valueOf(0).compareTo(userId) >= 0) {
			throw new ResourceNotFoundException("User ID does not exist.");
		}
		
		List<Asset> assets = getDAO().getAll(getEntityClass()).stream()
				.filter(asset -> asset.getUser().getId().equals(userId))
				.collect(Collectors.toList());
		return assets;
	}
	
	@Override
	@Transactional
	public Asset getUserAsset(Long assetId, Long userId) throws ResourceNotFoundException {
		if(userId == null || Long.valueOf(0).compareTo(userId) >= 0) {
			throw new ResourceNotFoundException("User ID does not exist.");
		}
		
		if(assetId == null || Long.valueOf(0).compareTo(assetId) >= 0) {
			throw new ResourceNotFoundException("Asset ID does not exist.");
		}
		
		Asset asset = listUserAssets(userId).stream()
				.filter(c -> c.getId().equals(assetId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("User with ID=[" + userId + "] does not have an asset with ID=[" + assetId + "]."));
		
		return asset;
	}
}
