package com.bmi.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.bmi.app.repository",
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager"
)
public class DBAppConfig {
	@Autowired
	private Environment env;
	@Primary
    @Bean
    @ConfigurationProperties(prefix="datasource.app")
    public DataSourceProperties appDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource appDataSource() {
        DataSourceProperties appDataSourceProperties = appDataSourceProperties();
		return DataSourceBuilder.create()
        			.driverClassName(appDataSourceProperties.getDriverClassName())
        			.url(appDataSourceProperties.getUrl())
        			.username(appDataSourceProperties.getUsername())
        			.password(appDataSourceProperties.getPassword())
        			.build();
    }
    @Primary
    @Bean
    public PlatformTransactionManager appTransactionManager()
    {
        EntityManagerFactory factory = appEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(appDataSource());
        factory.setPackagesToScan(new String[]{"com.bmi.app.entity"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);
        
        return factory;
    }
    /*  @Primary
    @Bean
	public DataSourceInitializer appDataSourceInitializer() 
	{
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(appDataSource());
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		//databasePopulator.addScript(new ClassPathResource("app-data.sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(env.getProperty("datasource.app.initialize", Boolean.class, false));
		return dataSourceInitializer;
	}*/
    
}
