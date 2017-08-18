package hobbydev.domain.core;

import hobbydev.domain.users.User;

import java.util.List;

public interface UserGroup extends Group {

    List<User> getUsers();
}
