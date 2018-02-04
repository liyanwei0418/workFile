package cn.gukeer.common.shiro.filter;

import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.GsonUtil;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by conn on 2016/7/29.
 */
@Component
public class LogoutAuthFilter extends LogoutFilter implements AjaxFilter {

    private String rst = GsonUtil.toJson(ResultEntity.newResultEntity(ResultEntity.ERR_CODE, "登录状态异常,请刷新页面", null));

    @Override
    protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
        System.out.println("====================LogoutAuthFilter:" + redirectUrl);
        if (AjaxUtil.handleAjaxAppRequest(request, response, this)) {
            return;
        }
        super.issueRedirect(request, response, redirectUrl);
    }

    @Override
    public String getRst(ServletRequest request, ServletResponse response) {
        return this.rst;
    }
}
