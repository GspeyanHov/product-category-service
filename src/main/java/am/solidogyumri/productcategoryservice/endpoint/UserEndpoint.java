package am.solidogyumri.productcategoryservice.endpoint;

import am.solidogyumri.productcategoryservice.dto.*;
import am.solidogyumri.productcategoryservice.entity.Product;
import am.solidogyumri.productcategoryservice.entity.Role;
import am.solidogyumri.productcategoryservice.entity.User;
import am.solidogyumri.productcategoryservice.mapper.ProductMapper;
import am.solidogyumri.productcategoryservice.mapper.UserMapper;
import am.solidogyumri.productcategoryservice.security.CurrentUser;
import am.solidogyumri.productcategoryservice.service.ProductService;
import am.solidogyumri.productcategoryservice.service.UserService;
import am.solidogyumri.productcategoryservice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserEndpoint {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody UserCreateDto userCreateDto) {
        Optional<User> byEmail = userService.findByEmail(userCreateDto.getEmail());
        if (byEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userMapper.map(userCreateDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userMapper.map(userService.save(user)));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authUser(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userService.findByEmail(userAuthDto.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .user(userMapper.map(user))
                        .token(jwtTokenUtil.generateToken(userAuthDto.getEmail()))
                        .build()
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

