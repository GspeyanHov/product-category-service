package am.solidogyumri.productcategoryservice.service;

import am.solidogyumri.productcategoryservice.entity.User;
import am.solidogyumri.productcategoryservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User save(User user) {
        if(user == null){
            throw new RuntimeException("user already exist");
        }
        return userRepository.save(user);
    }
}
