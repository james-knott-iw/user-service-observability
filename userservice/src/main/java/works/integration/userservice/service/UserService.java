package works.integration.userservice.service;

import works.integration.userservice.entity.User;

public interface UserService {

    public User getUser(Long id);

    public User saveUser(User user);

    public User updateUser(Long id, User user);

    public void deleteUser(Long id);

}
