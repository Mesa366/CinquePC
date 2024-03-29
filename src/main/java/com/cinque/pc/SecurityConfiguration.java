package com.cinque.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cinque.pc.Services.MyUserService;

@Configuration
@Order(1)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public MyUserService usuarioServicio;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .userDetailsService(usuarioServicio)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin().and()
        .authorizeRequests()
            .antMatchers("/css/*", "/js/*", "/img/*", "/**")
            .permitAll()
        .and().formLogin()
            .loginPage("/auth/form/login") // Que formulario esta mi login
                .loginProcessingUrl("/auth/logincheck")
                .usernameParameter("email") // Como viajan los datos del logueo
                .passwordParameter("password")// Como viajan los datos del logueo
                .defaultSuccessUrl("/")// A que URL viaja
                .permitAll()
        .and().logout() // Aca configuro la salida
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/")
            .permitAll().and().csrf().disable();
    }
}