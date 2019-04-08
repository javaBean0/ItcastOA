package cn.itcast.oa.base.impl;

import cn.itcast.oa.base.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;
    protected Class<T> clazz;

    public BaseDaoImpl(){
        //通过反射得到T的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
      //  System.out.println(clazz);
    }


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Object entity) {
        getSession().save(entity);
    }

    @Override
    public T getById(Long id) {
        return getSession().get(clazz, id);
    }

    @Override
    public void update(Object entity) {
        getSession().update(entity);

    }

    @Override
    public void delete(Long id) {
        T t = getSession().get(clazz, id);
        getSession().delete(t);

    }

    @Override
    public List findByIds(Long[] ids) {
        if(ids.length == 0 || ids == null){
            return Collections.emptyList();
        }
        return getSession().createQuery("from" + clazz.getSimpleName() +  "where id in (:ids)")
                .setParameterList("ids", ids).list();
    }

    public List<T> findAll() {
        return getSession().createQuery(//
                "FROM " + clazz.getSimpleName())//
                .list();
    }
}
