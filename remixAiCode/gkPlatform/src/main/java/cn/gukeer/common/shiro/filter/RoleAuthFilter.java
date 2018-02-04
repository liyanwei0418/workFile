package cn.gukeer.common.shiro.filter;

import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.GsonUtil;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by conn on 2016/7/29.
 */
@Component
public class RoleAuthFilter extends RolesAuthorizationFilter implements AjaxFilter {

    private String rst = GsonUtil.toJson(ResultEntity.newResultEntity(ResultEntity.ERR_CODE, "登录状态异常,请刷新页面", null));

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        return (!AjaxUtil.handleAjaxAppRequest(request, response, this)) && super.onAccessDenied(request, response);
    }

    @Override
    public String getRst(ServletRequest request, ServletResponse response) {
        return this.rst;
    }
}
