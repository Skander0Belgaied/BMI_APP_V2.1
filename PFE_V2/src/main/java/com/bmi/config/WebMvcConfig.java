/**
 * 
 */
package com.bmi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
	
	@Bean
    public OpenEntityManagerInViewFilter securityOpenEntityManagerInViewFilter()
    {
    	OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
    	osivFilter.setEntityManagerFactoryBeanName("appEntityManagerFactory");
    	return osivFilter;
    }
	
	@Bean
    public OpenEntityManagerInViewFilter ordersOpenEntityManagerInViewFilter()
    {
    	OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
    	osivFilter.setEntityManagerFactoryBeanName("rapportEntityManagerFactory");
    	return osivFilter;
    }
	
	/*@Bean
	public FilterRegistrationBean securityOSIVFilter() {
		OpenEntityManagerInViewFilter securityOsivFilter = new OpenEntityManagerInViewFilter();
		securityOsivFilter.setEntityManagerFactoryBeanName("securityEntityManagerFactory");
	    FilterRegistrationBean registration = new FilterRegistrationBean(securityOsivFilter);
	    registration.addUrlPatterns("/app/*");
	    //registration.addUrlPatterns("/*");
	    return registration;
	}
	
	@Bean
	public FilterRegistrationBean ordersOSIVFilter() {
		OpenEntityManagerInViewFilter ordersOsivFilter = new OpenEntityManagerInViewFilter();
		ordersOsivFilter.setEntityManagerFactoryBeanName("ordersEntityManagerFactory");
	    FilterRegistrationBean registration = new FilterRegistrationBean(ordersOsivFilter);
	    registration.addUrlPatterns("/orders/*");
	   // registration.addUrlPatterns("/*");
	    return registration;
	}*/
}