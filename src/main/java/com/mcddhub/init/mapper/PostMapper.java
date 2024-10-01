package com.mcddhub.init.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mcddhub.init.model.entity.Post;

import java.util.Date;
import java.util.List;

public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}