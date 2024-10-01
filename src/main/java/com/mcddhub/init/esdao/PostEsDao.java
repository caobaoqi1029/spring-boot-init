package com.mcddhub.init.esdao;

import java.util.List;

import com.mcddhub.init.model.dto.post.PostEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 * @author: caobaoqi1029
 * @date: 2024/10/1 11:51
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);
}