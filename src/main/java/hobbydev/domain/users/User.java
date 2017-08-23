package hobbydev.domain.users;

import hobbydev.domain.assets.Asset;
import hobbydev.domain.core.IdentifiedEntityInterface;
import hobbydev.domain.currencies.UserCurrency;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User implements IdentifiedEntityInterface, UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name = "restore_key")
	private String restoreKey = null;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "birth_date")
	private LocalDate birthDate = LocalDate.now();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserCurrency> currencies = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Asset> assets = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRestoreKey() {
		return restoreKey;
	}
	
	public void setRestoreKey(String restoreKey) {
		this.restoreKey = restoreKey;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<UserCurrency> getCurrencies() {
		return currencies;
	}
	
	public void setCurrencies(List<UserCurrency> currencies) {
		this.currencies = currencies;
	}
	
	public void addCurrency(UserCurrency currency) {
		if(currency == null) {
			return;
		}
		currencies.add(currency);
		currency.setUser(this);
	}
	
	public void removeCurrency(UserCurrency currency) {
		if(currency == null) {
			return;
		}
		currencies.remove(currency);
		currency.setUser(null);
	}
	
	public List<Asset> getAssets() {
		return assets;
	}
	
	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
	
	public void addAsset(Asset asset) {
		if(asset == null) {
			return;
		}
		assets.add(asset);
		asset.setUser(this);
	}
	
	public void removeAsset(Asset asset) {
		if(asset == null) {
			return;
		}
		assets.remove(asset);
		asset.setUser(null);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return password != null &&
				!password.trim().isEmpty() &&
				(restoreKey == null || restoreKey.trim().isEmpty());
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}
}
