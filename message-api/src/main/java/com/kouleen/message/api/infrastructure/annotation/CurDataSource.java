package com.kouleen.message.api.infrastructure.annotation;

import com.kouleen.message.api.infrastructure.constants.DataSourceType;

import java.lang.annotation.*;

/**
 * @author zhangqing
 * @since 2023/4/9 9:57
 */
@Target({ElementType.PACKAGE,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurDataSource {

    /**
     * 数据源切换标识
     * @return 数据源标识键
     */
    DataSourceType value() default DataSourceType.FIRST;
}
