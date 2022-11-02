package am.solidogyumri.productcategoryservice.security;

import am.solidogyumri.productcategoryservice.entity.User;
import am.solidogyumri.productcategoryservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if(userByEmail.isPresent()){
            return new CurrentUser(userByEmail.get());
        }
        throw new UsernameNotFoundException("User does not exist");
    }
}
