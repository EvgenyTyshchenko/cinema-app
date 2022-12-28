package cinema.config;

import static cinema.model.Role.RoleName.ADMIN;
import static cinema.model.Role.RoleName.USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers(HttpMethod.POST, "/cinema-halls").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET, "/movies").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers(HttpMethod.POST, "/movies").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET, "/movie-sessions/available")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/movie-sessions").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET, "/orders").hasRole(USER.name())
                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole(USER.name())
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasRole(USER.name())
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user").hasRole(USER.name())
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
