package com.ak.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Import(value = {ServiceConfiguration.class})
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests().antMatchers("/signup", "/login", "/css/**", "/img/**")
		.permitAll().antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
		.loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/bye").permitAll().and()
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
	builder.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery(
		"SELECT user.username as username, role.name as role FROM user INNER JOIN user_role ON "
			+ "user.id = user_role.user_id INNER JOIN role ON user_role.role_id = role.id WHERE user.username = ?")
		.usersByUsernameQuery("select username, password, enabled from user where user.username = ?")
		.passwordEncoder(new BCryptPasswordEncoder());
    }

}