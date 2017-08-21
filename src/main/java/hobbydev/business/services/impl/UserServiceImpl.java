package hobbydev.business.services.impl;

import hobbydev.business.AbstractService;
import hobbydev.business.exception.ResourceForbiddenOperationException;
import hobbydev.business.exception.ResourceNotFoundException;
import hobbydev.business.services.UserService;
import hobbydev.domain.currencies.UserCurrency;
import hobbydev.domain.users.User;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Service
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
    
    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User foundUser = listUsers().stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException("User with provided username was not found - " + username));
            return foundUser;
        } catch (UsernameNotFoundException unfe) {
            throw unfe;
        } catch (Throwable t) {
            throw new RuntimeException("Authentication service failure. Please contact the administrator", t);
        }
    }
    
    @Override
    @Transactional
    public List<User> listUsers() {
        return getDAO().getAll(getEntityClass());
    }
    
    @Override
    @Transactional
    public User getUser(Long id) throws ResourceNotFoundException {
        return listUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(id, getEntityClass().getSimpleName()));
    }
    
    @Override
    @Transactional
    public User addUser(User user) throws ResourceForbiddenOperationException {
        if(user == null) {
            throw new IllegalArgumentException("User is null");
        }
        
        user.setId(null);
        
        boolean emailDuplicate = listUsers().stream()
                .anyMatch(dbUser -> dbUser.getEmail().equalsIgnoreCase(user.getEmail()));
        if(emailDuplicate) {
            throw new ResourceForbiddenOperationException("User with the " + user.getEmail() + " email address is already registered");
        }
        
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        getDAO().create(user);
        
        return user;
    }
    
    @Override
    @Transactional
    public User updateUser(User user) throws ResourceNotFoundException, ResourceForbiddenOperationException {
        if(user == null) {
            throw new IllegalArgumentException("User is null");
        }
        
        if(user.getId() == null || Long.valueOf(0).compareTo(user.getId()) >= 0) {
            throw new ResourceNotFoundException("User ID does not exist. Only user with valid ID can be updated.");
        }
        
        // in the event of changing an email we should check that there are no other users with the same email but different ID
        boolean anotherWithSameEmail = listUsers().stream()
                .anyMatch(dbUser -> dbUser.getEmail().equalsIgnoreCase(user.getEmail()) && !dbUser.getId().equals(user.getId()));
        if(anotherWithSameEmail) {
            throw new ResourceForbiddenOperationException("User with the " + user.getEmail() + " email address is already registered");
        }
        
        User persistant = getUser(user.getId());
        persistant.setEmail(user.getEmail());
        persistant.setFirstName(user.getFirstName());
        persistant.setLastName(user.getLastName());
        persistant.setBirthDate(user.getBirthDate());
        
        return persistant;
    }
    
    @Override
    @Transactional
    public boolean deleteUser(Long id) throws ResourceForbiddenOperationException {
        if(id == null || Long.valueOf(0L).compareTo(id) >= 0) {
            throw new ResourceForbiddenOperationException("User can be deleted only by providing a valid ID.");
        }
        
        return delete(id);
    }
    
    @Override
    @Transactional
    public User changePassword(Long userId, String oldRawPass, String newRawPass) throws ResourceNotFoundException, ResourceForbiddenOperationException {
        if(userId == null || Long.valueOf(0).compareTo(userId) >= 0) {
            throw new ResourceNotFoundException("User ID does not exist. Password can only be changed for user with valid ID.");
        }
    
        if(oldRawPass == null || oldRawPass.trim().isEmpty()) {
            throw new IllegalArgumentException("Old password is not provided.");
        }
    
        if(newRawPass == null || newRawPass.trim().isEmpty()) {
            throw new IllegalArgumentException("New password is not provided.");
        }
        
        User persistant = getUser(userId);
        
        boolean passValid = passwordEncoder.isPasswordValid(persistant.getPassword(), oldRawPass, null);
        if(!passValid) {
            throw new ResourceForbiddenOperationException("Invalid password provided.");
        }
        
        persistant.setPassword(passwordEncoder.encodePassword(newRawPass, null));
        return persistant;
    }
    
    @Override
    @Transactional
    public String startPasswordRestore(String username) throws ResourceNotFoundException {
        User persistant = null;
        try {
            persistant = loadUserByUsername(username);
        } catch (UsernameNotFoundException unfe) {
            throw new ResourceNotFoundException("User [" + username + "] was not found.", unfe);
        }
    
        // generate random password
        // generate restore key
    
        String key = KeyGenerators.string().generateKey();
        String randomPassword = KeyGenerators.string().generateKey();
        
        persistant.setPassword(passwordEncoder.encodePassword(randomPassword, null));
        persistant.setRestoreKey(passwordEncoder.encodePassword(key, null));
        
        //mailService.sendPasswordRestoreInstructions(user, key);
        
        return key;
    }
    
    @Override
    @Transactional
    public boolean completePasswordRestore(String restoreKey, String newRawPassword) throws ResourceNotFoundException {
        // check that restore key is valid
        // set new pass
        // reset restore key
        
        User persistant = listUsers().stream()
                .filter(user -> passwordEncoder.isPasswordValid(user.getRestoreKey(), restoreKey, null))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Security key is not valid"));
        
        persistant.setPassword(passwordEncoder.encodePassword(newRawPassword, null));
        persistant.setRestoreKey(null);
        
        return true;
    }
    
    @Override
    @Transactional
    public UserCurrency addUserCurrency(Long userId, UserCurrency currency) throws ResourceForbiddenOperationException, ResourceNotFoundException {
        if(userId == null || Long.valueOf(0).compareTo(userId) >= 0) {
            throw new ResourceNotFoundException("User ID does not exist.");
        }
        
        if(currency == null || currency.getCode() == null || currency.getCode().trim().isEmpty()) {
            throw new ResourceForbiddenOperationException("Invalid currency data.");
        }
        
        User user = getUser(userId);
        boolean hasCurrency = user.getCurrencies().stream()
                .anyMatch(c -> c.getCode().equalsIgnoreCase(currency.getCode()));
        
        if(hasCurrency) {
            throw new ResourceForbiddenOperationException("User already has a [" + currency.getCode() + "] currency added");
        }
        
        user.addCurrency(currency);
        
        return currency;
    }
    
    @Override
    @Transactional
    public boolean deleteUserCurrency(Long userId, Long currencyId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
        if(userId == null || Long.valueOf(0).compareTo(userId) >= 0) {
            throw new ResourceNotFoundException("User ID does not exist.");
        }
        
        if(currencyId == null || Long.valueOf(0).compareTo(currencyId) >= 0) {
            throw new ResourceNotFoundException("Currency ID does not exist.");
        }
        
        User user = getUser(userId);
        boolean hasCurrency = user.getCurrencies().stream()
                .anyMatch(c -> c.getId().equals(currencyId));
        
        if(!hasCurrency) {
            return false;
        }
    
        UserCurrency currency = user.getCurrencies().stream()
                .filter(c -> c.getId().equals(currencyId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User with ID=[" + userId + "] does not have a currency with ID=[" + currencyId + "]."));
        
        user.removeCurrency(currency);
        
        return true;
    }
}
