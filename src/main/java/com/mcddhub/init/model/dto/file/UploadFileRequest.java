package com.mcddhub.init.model.dto.file;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 文件上传请求
 *
 */
@Data
@Component
public class UploadFileRequest implements Serializable {

    private String biz;

    private static final long serialVersionUID = 1L;
}