package org.octocode;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class InfrastructureConfig {
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        final ComboPooledDataSource ret = new ComboPooledDataSource();
        ret.setDriverClass("com.mysql.jdbc.Driver");
        ret.setUser("root");
        ret.setPassword("root");
        ret.setJdbcUrl("jdbc:mysql://localhost/ost");
        ret.setCheckoutTimeout(1000);
        ret.setUnreturnedConnectionTimeout(1000);
        return ret;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
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

        factory.setJpaProperties(new Properties() {{
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