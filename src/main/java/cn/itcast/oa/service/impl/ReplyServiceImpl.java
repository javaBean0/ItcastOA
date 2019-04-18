package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * author:bigStone
 * Date:2019/4/17
 **/
@Service
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService {

    @Override
    public List<Reply> findByTopic(Topic topic) {
        return getSession().createQuery(//
                // 排序：最前面的是最早发表的回帖
                "FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
                .setParameter(0, topic)//
                .list();
    }

}
