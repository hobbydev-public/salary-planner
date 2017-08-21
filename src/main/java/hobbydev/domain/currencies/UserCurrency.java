package hobbydev.domain.currencies;

import hobbydev.domain.core.IdentifiedEntityInterface;
import hobbydev.domain.users.User;

import javax.persistence.*;

@Entity
@Table(name = "user_currencies")
public class UserCurrency implements IdentifiedEntityInterface {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "symbol")
	private String symbol;
	@Column(name = "name")
	private String name;
	@Column(name = "native_symbol")
	private String nativeSymbol;
	@Column(name = "decimal_digits_count")
	private String decimalDigitsCount;
	@Column(name = "rounding")
	private String rounding;
	@Column(name = "code")
	private String code;
	@Column(name = "plural_name")
	private String pluralName;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
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
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		UserCurrency that = (UserCurrency) o;
		
		return getId().equals(that.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
