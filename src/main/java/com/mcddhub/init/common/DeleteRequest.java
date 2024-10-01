package com.mcddhub.init.common;

import java.io.Serializable;
import lombok.Data;

/**
 * 删除请求
 *
 * @author: caobaoqi1029
 * @date: 2024/10/1 11:51
 * */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}