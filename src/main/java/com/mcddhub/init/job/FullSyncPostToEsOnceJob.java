package com.mcddhub.init.job;

import cn.hutool.core.collection.CollUtil;
import com.mcddhub.init.esdao.PostEsDao;
import com.mcddhub.init.model.dto.post.PostEsDTO;
import com.mcddhub.init.model.entity.Post;
import com.mcddhub.init.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FullSyncPostToEsOnceJob
 *
 * @version 1.0.0
 * @author: caobaoqi1029
 * @date: 2024/10/1 13:09
 */
@Slf4j
public class FullSyncPostToEsOnceJob implements CommandLineRunner {
    @Resource
    private PostService postService;

    @Resource
    private PostEsDao postEsDao;

    @Override
    public void run(String... args) {
        List<Post> postList = postService.list();
        if (CollUtil.isEmpty(postList)) {
            return;
        }
        List<PostEsDTO> postEsDTOList = postList.stream().map(PostEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = postEsDTOList.size();
        log.info("FullSyncPostToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            postEsDao.saveAll(postEsDTOList.subList(i, end));
        }
        log.info("FullSyncPostToEs end, total {}", total);
    }
}
