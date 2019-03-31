package com.cognizant.outreach.microservices.gateway;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.outreach.microservices.gateway.jwt.JwtTokenAuthenticationFilter;
import com.cognizant.outreach.microservices.security.config.JwtAuthenticationConfig;

/**
 * Config role-based auth.
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationConfig config;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .anonymous()
                .and()
                    .exceptionHandling().authenticationEntryPoint(
                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                    .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                 // To allow angular preflight options requests without authentication
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/security/login").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("api/perfdata/perfmetrics/dashboard/**").hasAnyRole("ADMIN","PMO")
                .antMatchers("api/school/submitschool").hasAnyRole("ADMIN","PMO")
                .antMatchers("api/school/saveclassstudents").hasAnyRole("ADMIN","PMO")
                .antMatchers("api/school/student/uploadbulkdata").hasAnyRole("ADMIN","PMO");
    }
}

