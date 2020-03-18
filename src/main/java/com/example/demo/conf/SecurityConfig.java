package com.example.demo.conf;

import com.example.demo.constants.RequestUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("pass")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(RequestUrl.LOGIN).permitAll()
                .antMatchers(RequestUrl.USER).hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage(RequestUrl.LOGIN)
                .defaultSuccessUrl(RequestUrl.HOME)
                .usernameParameter("username")
                .passwordParameter("password")
                .and().sessionManagement().maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl(RequestUrl.LOGIN);

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(RequestUrl.LOGOUT))
                .logoutSuccessUrl(RequestUrl.LOGIN)
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
