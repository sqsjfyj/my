package org.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static Session getThreadLocalSession(){
//        Configuration configuration = new Configuration().configure();
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //Session session = sessionFactory.openSession();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

}
