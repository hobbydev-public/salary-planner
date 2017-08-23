package hobbydev.domain.assets;

import hobbydev.domain.core.IdentifiedEntityInterface;
import hobbydev.domain.currencies.UserCurrency;
import hobbydev.domain.transactions.TransactionParticipant;
import hobbydev.domain.users.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "assets")
public class Asset implements IdentifiedEntityInterface, TransactionParticipant {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private AssetType type;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@ManyToOne
	@JoinColumn(name = "currency_id")
	private UserCurrency currency;
	
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
	
	public AssetType getType() {
		return type;
	}
	
	public void setType(AssetType type) {
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
	
	public UserCurrency getCurrency() {
		return currency;
	}
	
	public void setCurrency(UserCurrency currency) {
		this.currency = currency;
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
		
		Asset asset = (Asset) o;
		
		return getId().equals(asset.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	
	@Override
	public String toTransactionParticipantString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("Asset [")
				.append(getName())
				.append("](")
				.append(getType().getLabel())
				.append(" | ")
				.append(getCurrency().getCode())
				.append(")");
		
		return stringBuilder.toString();
	}
}
