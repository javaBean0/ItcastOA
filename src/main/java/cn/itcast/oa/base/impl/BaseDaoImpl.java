package cn.itcast.oa.base.impl;

import cn.itcast.oa.base.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
@SuppressWarnings("unchecked")
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    protected Class<T> clazz;

    public BaseDaoImpl() {
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
        if(id == null){
            return null;
        }
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
    public List<T> getByIds(Long[] ids) {
        if (ids.length == 0 || ids == null) {
            return Collections.EMPTY_LIST;
        }
        return getSession().createQuery("FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)").setParameterList("ids", ids).list();
    }
    public List<T> getAByIds(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }

        return getSession().createQuery(//
                "FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")//
                .setParameterList("ids", ids)//
                .list();
    }

    public List<T> findAll() {
        return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
    }
}
