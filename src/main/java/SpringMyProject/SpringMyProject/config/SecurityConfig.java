package SpringMyProject.SpringMyProject.config;

import SpringMyProject.SpringMyProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable();
        http.exceptionHandling().accessDeniedPage("/accessdenied");
        http.
                authorizeRequests().antMatchers("/css/**", "/js/**").permitAll();

        http.formLogin()
                .loginProcessingUrl("/zaidi").permitAll()
                .loginPage("/signin").permitAll()
                .usernameParameter("u_email")
                .passwordParameter("u_password")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signin?error");
        http.logout().logoutSuccessUrl("/signin")
                .logoutUrl("/signout").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}