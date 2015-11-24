package net.hoyoung.weibospider.api.service;

import net.hoyoung.weibospider.api.BaseDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import weibo4j.model.Status;

import javax.annotation.PostConstruct;

/**
 * Created by hoyoung on 2015/11/23.
 */
@Service
public class StatusService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @PostConstruct
    public void updateMb4(){
//        Session session = hibernateTemplate.getSessionFactory().openSession();
//        session.createSQLQuery("SET NAMES 'utf8mb4'").executeUpdate();
    }
    public void saveOrUpdate(Status status){
        hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery("SET NAMES 'utf8mb4'").executeUpdate();
        hibernateTemplate.saveOrUpdate(status);
    }
}
