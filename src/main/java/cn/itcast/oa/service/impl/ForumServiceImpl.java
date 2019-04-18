package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumServiceImpl extends BaseDaoImpl<Forum> implements ForumService {

    @Override
    public void moveUp(Long id) {
        //获取要交换的两个Forum
        Forum forum = getById(id);
        Forum other = (Forum) getSession().createQuery(
                "FROM Forum f where f.position < ? ORDER BY f.position DESC")
                .setParameter(0, forum.getPosition())
                .setFirstResult(0).setMaxResults(1).uniqueResult();
        if(other == null){
            return ;
        }
        //交换position的值
        int temp = forum.getPosition();
        forum.setPosition(other.getPosition());
        other.setPosition(temp);
        //更新到数据库中
        //因为是持久化状态，所以不需要调用update方法

    }

    @Override
    public void moveDown(Long id) {
        //获取要交换的两个Forum
        Forum forum = getById(id);
        Forum other = (Forum) getSession().createQuery(
                "FROM Forum f where f.position > ? ORDER BY f.position ASC")
                .setParameter(0, forum.getPosition())
                .setFirstResult(0).setMaxResults(1).uniqueResult();
        if(other == null){
            return ;
        }
        //交换position的值
        int temp = forum.getPosition();
        forum.setPosition(other.getPosition());
        other.setPosition(temp);
        //更新到数据库中
        //因为是持久化状态，所以不需要调用update方法
    }
    @Override
    public List<Forum> findAll() {
        return getSession().createQuery("FROM Forum f ORDER BY f.position ASC").list();
    }




    public void saveForum(Forum forum) {
        //保存到DB， 会生成id的值
        getSession().save(forum);
        //指定position的值为最大
        forum.setPosition(forum.getId().intValue());
        //因为是持久化状态，所以不需要调用update方法。
    }


}
