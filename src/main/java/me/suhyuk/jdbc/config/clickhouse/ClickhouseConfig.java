package me.suhyuk.jdbc.config.clickhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource("classpath:/application.yml")
@EnableJpaRepositories(
        basePackages = "me.suhyuk.jdbc.repository.clickhouse",
        entityManagerFactoryRef = "clickhouseEntityManager",
        transactionManagerRef = "clickhouseTransactionManager"
)
public class ClickhouseConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean clickhouseEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(clickhouseDataSource());
        // entity
        em.setPackagesToScan("me.suhyuk.jdbc.model.clickhouse");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        // hibernate
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "me.suhyuk.jdbc.config.clickhouse.ClickhouseDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource clickhouseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager clickhouseTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(clickhouseDataSource());
        return transactionManager;
    }

    @Primary
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(clickhouseDataSource());
    }
}
