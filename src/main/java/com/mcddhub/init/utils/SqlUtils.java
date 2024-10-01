package com.mcddhub.init.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * SqlUtils
 *
 * @version 1.0.0
 * @author: caobaoqi1029
 * @date: 2024/10/1 12:25
 */
public class SqlUtils {
    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField sortField
     * @return valid code
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
