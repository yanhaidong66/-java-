package top.haidong556.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.haidong556.oauth.service.impl.UserServiceImpl;


@Configuration
public class SecurityConfig  {
    @Autowired
    private UserServiceImpl userService;
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   MyLoginFilter loginFilter,
                                                   MyAuthenticationHandler authenticationHandler
                                                   ) throws Exception {
        http.authorizeHttpRequests(auth->{
            auth.anyRequest().authenticated();
        });

        http.formLogin(auth->{
            auth.loginPage("/login");
        });



        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);

        http.logout(auth->{
            auth.logoutSuccessHandler(authenticationHandler);
        });


        http.csrf(auth->{
            auth.disable();
        });


        http.sessionManagement(auth->{
            auth.disable();
                });

        http.rememberMe(auth->{
            auth.disable();
        });

        http.exceptionHandling(auth->{
            auth.authenticationEntryPoint(authenticationHandler)
                    .accessDeniedHandler(authenticationHandler)
                    .authenticationEntryPoint(authenticationHandler);
        });

        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        ProviderManager providerManager=new ProviderManager(daoAuthenticationProvider);
        return providerManager;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserServiceImpl userService) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setUserDetailsPasswordService(userService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }











}
