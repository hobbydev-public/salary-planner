package hobbydev.api.models.be;

import hobbydev.api.models.be.generic.AbstractModel;
import hobbydev.domain.currencies.UserCurrency;

public class UserCurrencyModel extends AbstractModel {
	
	private String symbol;
	private String name;
	private String nativeSymbol;
	private String decimalDigitsCount;
	private String rounding;
	private String code;
	private String pluralName;
	
	private UserModel user;
	
	protected UserCurrencyModel(){}
	
	public UserCurrencyModel(UserCurrency domain) {
		super(domain);
		
		setSymbol(domain.getSymbol());
		setName(domain.getName());
		setNativeSymbol(domain.getNativeSymbol());
		setDecimalDigitsCount(domain.getDecimalDigitsCount());
		setRounding(domain.getRounding());
		setCode(domain.getCode());
		setPluralName(domain.getPluralName());
		setUser(new UserModel(domain.getUser(), false));
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
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
	
	public UserModel getUser() {
		return user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}
}
