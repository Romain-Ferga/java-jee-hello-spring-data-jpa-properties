package com.spring.data.jpa.entity;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.spring.data.jpa.repository.ClientRepository;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.spring.data.jpa.repository")
// @EnableJpaRepositories enables usage of JPA repositories
public class ConfigRepo2 {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";

	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));

		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));

		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));

		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();

		emfBean.setDataSource(dataSource());

		emfBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		// Hibernate will be used as JPA implementation.
		emfBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

		emfBean.setJpaProperties(hibProperties());

		return emfBean;

	}

	private Properties hibProperties() {

		Properties properties = new Properties();

		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));

		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

		return properties;

	}

	@Bean
	public JpaTransactionManager transactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;

	}

	public static void main(String[] args) {

		try {

			System.out.println("Début ….");

			ApplicationContext ctxt = new AnnotationConfigApplicationContext(ConfigRepo2.class);

			ClientRepository clientRepository = ctxt.getBean(ClientRepository.class);

			final List<Client> clients = clientRepository.findByNomStartingWith("a", new Sort(Direction.DESC, "nom"));

			for (Client client : clients) {

				System.out.println("---findByNomStartingWith---");

				System.out.println(client);

			}

			List<Client> clientsSorted = clientRepository.findAllSortedByNomPrenom();

			for (Client client : clientsSorted) {

				System.out.println("---findAllSortedByNomPrenom---");

				System.out.println(client);

			}

			Client clientByNum = clientRepository.findOneByNumeroV1("0145242020");

			System.out.println("---findOneByNumeroV1---");

			System.out.println(clientByNum);

			Client clientByNumV2 = clientRepository.findOneByNumeroV2("0987654321");

			System.out.println("---findOneByNumeroV2---");

			System.out.println(clientByNumV2);

			List<Client> clientsByDep = clientRepository.findAllByNomAndDepartement("yeti", "312010");

			System.out.println("---findAllByNomAndDepartement---");

			for (Client client : clientsByDep) {

				System.out.println(client);

			}

			System.out.println("Done.");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
