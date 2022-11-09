package am.solidogyumri.productcategoryservice.service;

import am.solidogyumri.productcategoryservice.dto.UserAuthResponseDto;
import am.solidogyumri.productcategoryservice.dto.UserCreateDto;
import am.solidogyumri.productcategoryservice.dto.UserDto;
import am.solidogyumri.productcategoryservice.entity.Role;
import am.solidogyumri.productcategoryservice.entity.User;
import am.solidogyumri.productcategoryservice.repository.UserRepository;
import am.solidogyumri.productcategoryservice.security.CurrentUser;
import am.solidogyumri.productcategoryservice.security.JwtAuthenticationTokenFilter;
import am.solidogyumri.productcategoryservice.security.UserDetailServiceImpl;
import am.solidogyumri.productcategoryservice.util.JwtTokenUtil;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Test
    void registerUser() {

        User user = User.builder()
                .id(1)
                .email("email@mail.com")
                .name("Poxos")
                .surname("Poxosyan")
                .password(passwordEncoder.encode("poxos"))
                .role(Role.USER)
                .build();

        when(userRepository.save(any())).thenReturn(user);

        userService.save(User.builder()
                .email("email@mail.com")
                .name("Poxos")
                .surname("Poxosyan")
                .password(passwordEncoder.encode("poxos"))
                .role(Role.USER)
                .build());
        verify(userRepository, times(1)).save(any());

    }

    @Test
    void register_null() {
        User user = User.builder()
                .id(1)
                .email("email@mail.com")
                .name("Poxos")
                .surname("Poxosyan")
                .password(passwordEncoder.encode("poxos"))
                .role(Role.USER)
                .build();
        when(userRepository.save(any())).thenReturn(user);

        assertThrows(RuntimeException.class, () -> {
            userService.save(null);
        });
        verify(userRepository, times(0)).save(any());

    }

    @Test
    void auth_user() throws Exception {

        UserCreateDto userCreateDto = new UserCreateDto();
        UserAuthResponseDto userAuthResponseDto = new UserAuthResponseDto();

        String jsonCreate = userCreateDto.toString();
        String jsonResponse = userAuthResponseDto.toString();


    }

}
