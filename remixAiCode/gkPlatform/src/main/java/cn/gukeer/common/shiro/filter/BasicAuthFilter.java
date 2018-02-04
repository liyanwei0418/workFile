package cn.gukeer.common.shiro.filter;

import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.GsonUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by conn on 2016/7/29.
 */
@Component
public class BasicAuthFilter extends FormAuthenticationFilter implements AjaxFilter {

    private String rst = GsonUtil.toJson(ResultEntity.newResultEntity(ResultEntity.ERR_CODE, "登录状态异常,请刷新页面", null));

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return (!AjaxUtil.handleAjaxAppRequest(request, response, this)) && super.onAccessDenied(request, response);
    }

    @Override
    public String getRst(ServletRequest request, ServletResponse response) {
        return this.rst;
    }
}
