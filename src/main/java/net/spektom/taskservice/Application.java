package net.spektom.taskservice;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.mariadb.jdbc.MySQLDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class is responsible for:
 * <ol>
 * <li>Setting up DB connection and ORM</li>
 * <li>Initializing REST service for all found entities</li>
 * <li>Starting Spring application (main entry point)</li>
 * </ol>
 */
@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableTransactionManagement
public class Application extends RepositoryRestMvcConfiguration {

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		MySQLDataSource dataSource = new MySQLDataSource();
		dataSource.setDatabaseName("taskdb");
		dataSource.setUserName("user");
		dataSource.setPassword("pass");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		// Database tables will be created/updated automatically due to this:
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabase(Database.MYSQL);

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactoryBean.setPackagesToScan(getClass().getPackage().getName());
		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
