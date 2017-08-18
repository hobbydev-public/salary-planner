package hobbydev.api.models.be;

import hobbydev.api.models.be.generic.AbstractModel;
import hobbydev.domain.users.User;
import hobbydev.utils.DateUtils;

import java.util.Date;

public class PrincipalModel extends AbstractModel {
    
    protected String email;
    protected String firstName;
    protected String lastName;
    protected Date birthDate;
    protected boolean authenticated;
    
    protected PrincipalModel(){}
    
    public PrincipalModel(User domain) {
        super(domain);
        
        this.email = domain.getEmail();
        this.firstName = domain.getFirstName();
        this.lastName = domain.getLastName();
        this.birthDate = DateUtils.toDate(domain.getBirthDate());
        this.authenticated = true;
    }
    
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
    
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    public Date getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
