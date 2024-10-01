package com.mcddhub.init.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mcddhub.init.model.dto.post.PostQueryRequest;
import com.mcddhub.init.model.entity.Post;
import com.mcddhub.init.model.vo.PostVO;

import javax.servlet.http.HttpServletRequest;

public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);

    /**
     * 从 ES 查询
     *
     */
    Page<Post> searchFromEs(PostQueryRequest postQueryRequest);

    /**
     * 获取帖子封装
     *
     */
    PostVO getPostVO(Post post, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, HttpServletRequest request);
}