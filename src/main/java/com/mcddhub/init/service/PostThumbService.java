package com.mcddhub.init.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mcddhub.init.model.entity.PostThumb;
import com.mcddhub.init.model.entity.User;

public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     */
    int doPostThumb(long postId, User loginUser);

    /**
     * 帖子点赞（内部服务）
     */
    int doPostThumbInner(long userId, long postId);
}