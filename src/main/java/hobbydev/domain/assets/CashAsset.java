package hobbydev.domain.assets;

import java.math.BigDecimal;

public class CashAsset implements Asset {
	
	private Long id;
	private AssetType type;
	private String name;
	private BigDecimal balance;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public AssetType getType() {
		return type;
	}
	
	@Override
	public void setType(AssetType type) {
		this.type = type;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public BigDecimal getBalance() {
		return balance;
	}
	
	@Override
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		CashAsset cashAsset = (CashAsset) o;
		
		return getId().equals(cashAsset.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
