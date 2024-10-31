package lk.ijse.db;

import lk.ijse.orm_couse_work.entity.CulinaryProgram;
import lk.ijse.orm_couse_work.entity.Enrollment;
import lk.ijse.orm_couse_work.entity.Student;
import lk.ijse.orm_couse_work.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        // Load configuration from the hibernate.properties file
        configuration.configure("/lk/ijse/orm_couse_work/hibernate.properties")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(CulinaryProgram.class)
                .addAnnotatedClass(Enrollment.class)
                .addAnnotatedClass(Student.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
}

