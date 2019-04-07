package cn.itcast.oa.service;

import cn.itcast.oa.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveTwoUser(){
        Session session = sessionFactory.getCurrentSession();
        session.save(new User());
        int a = 1 / 0;
        session.save(new User());


    }
}
