package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * author:bigStone
 * Date:2019/4/17
 **/
@Service
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService {

    public List<Topic> findByForum(Forum forum) {
        return getSession().createQuery(//
                // TODO 排序：所有置顶帖都在最上面，然后把最新状态的主题显示到前面
                "FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
                .setParameter(0, forum)//
                .list();
    }

    public void saveTopic(Topic topic) {
        // 1，设置属性并保存
        // topic.setType(Topic.TYPE_NORMAL); // 默认为“普通”
        // topic.setReplyCount(0); // 默认为0
        // topic.setLastReply(null); // 默认为null
        topic.setLastUpdateTime(topic.getPostTime()); // 默认为主题的发表时间
        getSession().save(topic); // 保存

        // 2，维护相关的信息
        Forum forum = topic.getForum();
        forum.setTopicCount(forum.getTopicCount() + 1); // 主题数
        forum.setArticleCount(forum.getArticleCount() + 1); // 文章数（主题+回复）
        forum.setLastTopic(topic); // 最后发表的主题
        getSession().update(forum); // 更新
    }

    @Override
    public void saveTopic(Reply reply) {
        //保存到数据库
        getSession().save(reply);
        //维护相关的信息
        Topic topic = reply.getTopic();
        Forum forum = topic.getForum();


        forum.setArticleCount(forum.getArticleCount() + 1);
        topic.setReplyCount(topic.getReplyCount() + 1);
        topic.setLastReply(reply);
        topic.setLastUpdateTime(reply.getPostTime());

        getSession().save(topic);
        getSession().save(forum);

    }
}
