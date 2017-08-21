package hobbydev.api.models.fe;

import hobbydev.api.models.fe.generic.View;
import hobbydev.domain.currencies.UserCurrency;

public class UserCurrencyView implements View<UserCurrency> {
	
	private Long id;
	private String symbol;
	private String name;
	private String nativeSymbol;
	private String decimalDigitsCount;
	private String rounding;
	private String code;
	private String pluralName;
	
	@Override
	public UserCurrency toDomain() {
		UserCurrency domain = new UserCurrency();
		
		domain.setId(this.id);
		domain.setSymbol(this.symbol);
		domain.setName(this.name);
		domain.setNativeSymbol(this.nativeSymbol);
		domain.setDecimalDigitsCount(this.decimalDigitsCount);
		domain.setRounding(this.rounding);
		domain.setCode(this.code);
		domain.setPluralName(this.pluralName);
		
		return domain;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNativeSymbol() {
		return nativeSymbol;
	}
	
	public void setNativeSymbol(String nativeSymbol) {
		this.nativeSymbol = nativeSymbol;
	}
	
	public String getDecimalDigitsCount() {
		return decimalDigitsCount;
	}
	
	public void setDecimalDigitsCount(String decimalDigitsCount) {
		this.decimalDigitsCount = decimalDigitsCount;
	}
	
	public String getRounding() {
		return rounding;
	}
	
	public void setRounding(String rounding) {
		this.rounding = rounding;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPluralName() {
		return pluralName;
	}
	
	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
	}
}
