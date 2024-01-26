package works.integration.userservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import works.integration.userservice.entity.User;
import works.integration.userservice.exception.EntityNotFoundException;
import works.integration.userservice.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return unwrap(userRepository.findById(id), id);
    }

    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }

    @Override
    public User updateUser(Long id, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUSer'");
    }

    private User unwrap(Optional<User> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {

            throw new EntityNotFoundException(id, User.class);
        }
    }

}
