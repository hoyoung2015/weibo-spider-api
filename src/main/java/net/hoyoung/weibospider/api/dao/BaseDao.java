package net.hoyoung.weibospider.api.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hoyoung on 2015/11/23.
 */
@Repository
public class BaseDao {
    @Autowired
    protected HibernateTemplate hibernateTemplate;
    public Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
}
