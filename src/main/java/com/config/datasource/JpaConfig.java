//package com.config.datasource;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//
//@Configuration
////@EnableJpaRepositories --> JPA Repository 활성화
//public class JpaConfig  {
//
//    public JpaConfig(){
//        System.out.println(this.getClass().getName());
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
//        LocalContainerEntityManagerFactoryBean lem = new LocalContainerEntityManagerFactoryBean();
//        lem.setDataSource(dataSource);
//        lem.setPackagesToScan(new String[] { "com.config.domain.entity" });
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//        lem.setJpaVendorAdapter(vendorAdapter);
//        lem.setJpaProperties(additionalProperties());
//
//        return lem;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    private Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        return properties;
//    }
//}
