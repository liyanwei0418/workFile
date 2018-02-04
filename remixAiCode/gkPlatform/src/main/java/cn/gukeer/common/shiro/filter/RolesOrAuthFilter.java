package cn.gukeer.common.shiro.filter;

import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.GsonUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by conn on 2016/7/29.
 */
@Component
public class RolesOrAuthFilter extends RolesAuthorizationFilter implements AjaxFilter {

    private String rst = GsonUtil.toJson(ResultEntity.newResultEntity(ResultEntity.ERR_CODE, "登录状态异常,请刷新页面", null));

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问
            return true;
        }
        for (int i = 0; i < rolesArray.length; i++) {
            if (subject.hasRole(rolesArray[i])) { //若当前用户是rolesArray中的任何一个，则有权限访问
                return true;
            }
        }

        return false;
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
