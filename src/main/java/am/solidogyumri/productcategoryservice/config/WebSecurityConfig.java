package am.solidogyumri.productcategoryservice.config;

import am.solidogyumri.productcategoryservice.entity.Role;
import am.solidogyumri.productcategoryservice.security.JwtAuthenticationEntryPoint;
import am.solidogyumri.productcategoryservice.security.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/categories").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/products").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.PUT,"/categories").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/products").hasAuthority(Role.USER.name())
                .anyRequest()
                .permitAll();
        http
                .addFilterBefore(filterBean(), UsernamePasswordAuthenticationFilter.class);
        http
                .headers().cacheControl();
    }

    @Bean
    public JwtAuthenticationTokenFilter filterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
