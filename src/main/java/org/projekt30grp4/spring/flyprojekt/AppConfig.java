package org.projekt30grp4.spring.flyprojekt;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
// Sucht die repositories im angegebenen Pfad
@EnableJpaRepositories(basePackages = "org.projekt30grp4.spring.flyprojekt.repositories")
@ComponentScan(basePackages = "org.projekt30grp4.spring.flyprojekt")
public class AppConfig {

    //Verbindungsmanager der persistence Settings liest
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("Grp4");
        return emfb;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

}
