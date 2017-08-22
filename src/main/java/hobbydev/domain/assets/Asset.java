package hobbydev.domain.assets;

import hobbydev.domain.core.IdentifiedEntityInterface;

import java.math.BigDecimal;

public interface Asset extends IdentifiedEntityInterface {
	
	AssetType getType();
	void setType(AssetType type);
	String getName();
	void setName(String name);
	BigDecimal getBalance();
	void setBalance(BigDecimal balance);
}
