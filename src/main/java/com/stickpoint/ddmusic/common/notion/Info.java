package com.stickpoint.ddmusic.common.notion;

import java.lang.annotation.*;

/**
 * @author Write By fntp
 * @title: Info
 * @projectName yyzq-front
 * @Modifier: fntp
 * @date 2021/9/18-17:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE,
        ElementType.PARAMETER,
        ElementType.PACKAGE,
        ElementType.LOCAL_VARIABLE})
public @interface Info {
    /**
     * 自定义注解 类比注释 但是可以直接取出
     * @return 返回的是注解内容的值
     */
    String value();
}
