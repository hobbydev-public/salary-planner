package hobbydev.api.models.be;

import hobbydev.api.models.be.generic.AbstractModel;
import hobbydev.domain.assets.Asset;

import java.math.BigDecimal;

public class AssetModel extends AbstractModel {
	
	private EnumModel type;
	private String name;
	private BigDecimal balance;
	private UserCurrencyModel currency;
	
	protected AssetModel(){}
	
	public AssetModel(Asset domain) {
		super(domain);
		
		setType(new EnumModel(domain.getType()));
		setName(domain.getName());
		setBalance(domain.getBalance());
		setCurrency(new UserCurrencyModel(domain.getCurrency()));
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public EnumModel getType() {
		return type;
	}
	
	public void setType(EnumModel type) {
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
	
	public UserCurrencyModel getCurrency() {
		return currency;
	}
	
	public void setCurrency(UserCurrencyModel currency) {
		this.currency = currency;
	}
}
