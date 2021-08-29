package com.springland365.SpringSecuritySimpleOath2Demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.oauth2Login();
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
                //.permitAll();
    }

    @Bean
    protected ClientRegistration buildCustomizedClientRegistration(
            @Value("${github.oauth2.client-id}")    String clientId ,
            @Value("${github.oauth2.client-secret}") String clientSecret )

    {
        ClientRegistration  cr =
                ClientRegistration.withRegistrationId("github")
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .scope(new String[]{"read:user"})
                        .authorizationUri("https://github.com/login/oauth/authorize")
                        .tokenUri("https://github.com/login/oauth/access_token")
                        .userInfoUri("https://api.github.com/user")
                        .userNameAttributeName("id")
                        .clientName("GitHub")
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
                        .build();
        return cr ;
    }

    //@Bean
    protected ClientRegistration    githubClientRegistration(
            @Value("${github.oauth2.client-id}")    String clientId ,
            @Value("${github.oauth2.client-secret}") String clientSecret )
    {
        ClientRegistration cr =
                CommonOAuth2Provider.GITHUB
                        .getBuilder("github")
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .build() ;

        return cr ;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration cr)
    {
        return new InMemoryClientRegistrationRepository(cr);
    }


}
