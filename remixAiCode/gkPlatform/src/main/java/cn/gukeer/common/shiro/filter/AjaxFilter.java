package cn.gukeer.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by conn on 2016/7/29.
 */
public interface AjaxFilter {

    String getRst(ServletRequest request, ServletResponse response);
}
