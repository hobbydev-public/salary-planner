package hobbydev.api.models.fe;

import hobbydev.api.models.fe.generic.View;
import hobbydev.domain.assets.Asset;
import hobbydev.domain.assets.AssetType;

import java.math.BigDecimal;

public class AssetView implements View<Asset> {
	
	private Long id;
	private String type;
	private String name;
	private BigDecimal balance;
	private UserCurrencyView currency;
	
	@Override
	public Asset toDomain() {
		Asset domain = new Asset();
		
		domain.setId(this.id);
		domain.setType(AssetType.valueOf(this.type));
		domain.setName(this.name);
		domain.setBalance(this.balance);
		domain.setCurrency(this.currency.toDomain());
		
		return domain;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public UserCurrencyView getCurrency() {
		return currency;
	}
	
	public void setCurrency(UserCurrencyView currency) {
		this.currency = currency;
	}
}
