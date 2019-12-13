package com.john.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.john.api.service.UserService;


@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	public void configure (final AuthorizationServerSecurityConfigurer oauthServer) throws Exception
	{
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");	
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception
	{
		clients.inMemory().withClient("fooClientId").secret("secret")
		.authorizedGrantTypes("password","authorization_code","refresh_token").scopes("read","write")
		.authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT","USER","ADMIN")
		.autoApprove(true)
		.accessTokenValiditySeconds(30)//tOKEN is valid for only 30 seconds
		.refreshTokenValiditySeconds(300);//Refresh Token validity is 5 minutes
	}
	
	@Override
	public void configure (final AuthorizationServerEndpointsConfigurer endpoints) throws Exception 
	{
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
		.accessTokenConverter(defaultAccessTokenConverter()).
		userDetailsService((UserDetailsService) userService);
	}

	@Bean
	public TokenStore tokenStore()
	{
		return new JwtTokenStore(defaultAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter defaultAccessTokenConverter()
	{
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("123");
		return converter;
	}
}
