package cn.gukeer.sync.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lx on 2016/11/30.
 */
//描述那些字段是自增的
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Increment {
}
