package com.inz.demo.configuration;

import com.inz.demo.util.model.auth.TokenUtil;
import com.inz.demo.util.model.auth.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenUtil tokenUtil;
    private final UserAuthService userAuthService;

    @Autowired
    public SecurityConfig(UserAuthService userAuthService, TokenUtil tokenUtil) {
        this.userAuthService = userAuthService;
        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().and()
                .anonymous().and()
                .csrf().disable()
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .addFilterBefore(new VerifyTokenFilter(tokenUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new GenerateTokenForUserFilter("/session", authenticationManager(), tokenUtil), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**", "/session", "/home/**", "/login/**", "/logout/**", "/users/**", "/user/**", "/swagger-ui/**",
                        "/teacher/**", "/subjects/**", "/lessons/**", "/students/**", "/presences/**", "/notifications/**", "/studentReport/**", "/logs/**")
                .permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


}