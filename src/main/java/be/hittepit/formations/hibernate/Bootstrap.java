package be.hittepit.formations.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

public class Bootstrap {
    private SessionFactory sessionFactory;

    private List<Class<?>> entityClasses;

    public Bootstrap(Class<?>... entityClasses) {
        this.entityClasses = Arrays.asList(entityClasses);
    }

    public SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            MetadataSources metadataSources = new MetadataSources(standardRegistry);
            for(Class<?> clazz:entityClasses) {
                metadataSources.addAnnotatedClass(clazz);
            }
            Metadata metadata = metadataSources.getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }
}
