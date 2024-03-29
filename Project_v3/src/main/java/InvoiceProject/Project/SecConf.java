package InvoiceProject.Project;


import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import InvoiceProject.Project.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	public class SecConf extends WebSecurityConfigurerAdapter {
		
		

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    	http
        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
      .formLogin()
          .defaultSuccessUrl("/invoices", true)
          .permitAll()
          .and()
      .logout()
          .permitAll();
  
    	  
    	  
       
    }
   
    
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
}
