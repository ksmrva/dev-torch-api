package io.ksmrva.visual.torch.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private static final String JDBC_URL_SCHEME = "jdbc";

    @Value("${postgresql.db.provider}")
    private String provider;

    @Value("${postgresql.db.driver}")
    private String driverClassName;

    @Value("${postgresql.db.host}")
    private String hostname;

    @Value("${postgresql.db.port}")
    private String port;

    @Value("${postgresql.db.name}")
    private String databaseName;

    @Value("${postgresql.db.user}")
    private String databaseUser;

    @Value("${postgresql.db.pass}")
    private String databasePass;

    public final ObjectMapper objectMapper;

    public DatabaseConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(createJdbcUrl());
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.username(databaseUser);
        dataSourceBuilder.password(databasePass);

        return dataSourceBuilder.build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("io.ksmrva.visual.torch");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());

        return localSessionFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        // if(isDebug) {
        //    hibernateProperties.put("hibernate.show_sql", "true");
        // }

        return hibernateProperties;
    }

    private String createJdbcUrl() {
        return JDBC_URL_SCHEME + ":" + provider + "://" + hostname + ":" + port + "/" + databaseName;
    }

}
