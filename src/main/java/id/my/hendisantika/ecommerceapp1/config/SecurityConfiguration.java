package id.my.hendisantika.ecommerceapp1.config;

import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.41
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username " + username + " not found.");
            }
            String role = user.getRole().equals("ROLE_ADMIN") ? "ADMIN" : "USER";

            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword())  // Password is already hashed in database
                    .roles(role)
                    .build();
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter {

        @Bean
        SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
            http.securityMatcher("/admin/**")
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers("/admin/login").permitAll()
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                    )
                    .formLogin(login -> login
                            .loginPage("/admin/login")
                            .loginProcessingUrl("/admin/loginvalidate")
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/admin/"); // Redirect on success
                            })
                            .failureHandler((request, response, exception) -> {
                                response.sendRedirect("/admin/login?error=true"); // Redirect on failure
                            }))

                    .logout(logout -> logout.logoutUrl("/admin/logout")
                            .logoutSuccessUrl("/admin/login")
                            .deleteCookies("JSESSIONID"))
                    .exceptionHandling(exception -> exception
                            .accessDeniedPage("/403")  // Custom 403 page
                    );
            http.csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
    }

    @Configuration
    @Order(2)
    public static class UserConfigurationAdapter {

        @Bean
        SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(requests -> requests
                            .requestMatchers("/", "/login", "/register", "/newuserregister", "/test", "/test2", "/css/**", "/js/**", "/images/**").permitAll()
                            .requestMatchers("/**").hasRole("USER"))
                    .formLogin(login -> login
                            .loginPage("/login")
                            .loginProcessingUrl("/userloginvalidate")
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/"); // Redirect on success
                            })
                            .failureHandler((request, response, exception) -> {
                                response.sendRedirect("/login?error=true"); // Redirect on failure
                            }))

                    .logout(logout -> logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .deleteCookies("JSESSIONID"))
                    .exceptionHandling(exception -> exception
                            .accessDeniedPage("/403")  // Custom 403 page
                    );

            http.csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
    }

}