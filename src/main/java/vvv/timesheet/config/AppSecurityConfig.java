package vvv.timesheet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ldap.url:ldap://oblgaz.brest.by/}") private String url;
    @Value("${ldap.domain:oblgaz.brest.by}") private String domain;
    @Value("${ldap.rootDN:ou=oblgaz,dc=oblgaz,dc=brest,dc=by}") private String rootDN;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
        //комментируем, попробуем отключить csrf (межсайтовая подделка запроса)
        //http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().csrf().disable().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       ActiveDirectoryLdapAuthenticationProvider adProvider = new ActiveDirectoryLdapAuthenticationProvider(domain, url, rootDN);
        adProvider.setConvertSubErrorCodesToExceptions(true);
        adProvider.setUseAuthenticationRequestCredentials(true);
        //auth.authenticationProvider(adProvider).userDetailsService(userDetailsService());
        auth.authenticationProvider(adProvider);
   }
}
