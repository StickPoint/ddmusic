package com.stickpoint.ddmusic.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: TableColumnField
 *
 * @ClassName : TableColumnField
 * @Date 2023/5/27 9:03
 * @Author gd
 * @PackageName com.stickpoint.ddmusic.common.annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableColumnField {
    String value();
}
