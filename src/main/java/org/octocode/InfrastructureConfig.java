package org.octocode;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Common infrastructure configuration class to setup a Spring container and infrastructure components like a
 * {@link DataSource}, a {@link EntityManagerFactory} and a {@link PlatformTransactionManager}. Will be used by the
 * configuration activating the plain JPA based repository configuration (see {@link PlainJpaConfig}) as well as the
 * Spring Data JPA based one (see {@link ApplicationConfig}).
 *
 * @author Oliver Gierke
 */
@Configuration
@EnableTransactionManagement
public class InfrastructureConfig {

    /**
     * Bootstraps an in-memory HSQL database.
     *
     * @return ://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/jdbc.html#jdbc-embedded-database
     * -support
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return builder.setType(EmbeddedDatabaseType.HSQL).build();
        final ComboPooledDataSource ret = new ComboPooledDataSource();
        ret.setDriverClass("com.mysql.jdbc.Driver");
        ret.setUser("root");
        ret.setPassword("root");
        ret.setJdbcUrl("jdbc:mysql://localhost/ost");
        ret.setCheckoutTimeout(1000);
        ret.setUnreturnedConnectionTimeout(1000);
        return ret;
    }

    /**
     * Sets up a {@link LocalContainerEntityManagerFactoryBean} to use Hibernate. Activates picking up entities from the
     * project's base package.
     *
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.HSQL);
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(getClass().getPackage().getName());
        try {
            factory.setDataSource(dataSource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        factory.setJpaProperties(new Properties(){{
            put("hibernate.hbm2ddl.auto", "create-drop");
        }});
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }
}