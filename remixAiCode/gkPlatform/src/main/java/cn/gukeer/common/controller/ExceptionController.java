package cn.gukeer.common.controller;

import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.exception.*;
import cn.gukeer.common.shiro.filter.AjaxUtil;
import cn.gukeer.common.utils.GsonUtil;
import cn.gukeer.common.utils.LoggerWrapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * Created by conn on 2016/8/9.
 */
@ControllerAdvice
public class ExceptionController extends LoggerWrapper {

    public final static String ERROR_HANDLER_RESULT_MSG = "error_handler_result_msg";
    public final static Pattern JSON_ACCEPT_URI_PATTERN = Pattern.compile("^/v[0-9]*\\.[0-9]*/.*");

    @ExceptionHandler
    public void handleErrPage(ErrcodeException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (handleException(e, e.getObj(), request, response)) {
            return;
        }
        if (e.getObj() != null && !StringUtils.isEmpty(e.getObj().getMsg())) {
            request.getSession().setAttribute(ERROR_HANDLER_RESULT_MSG, e.getObj().getMsg());
        }
        response.sendRedirect(request.getContextPath() + "/err");
    }

    @ExceptionHandler
    public void handleErrPage(LoginException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (handleException(e, -1000, "登录异常，请重新登录", request, response)) {
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @ExceptionHandler
    public void handleErrPage(PermissionException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (handleException(e, -1000, "没有操作权限", request, response)) {
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @ExceptionHandler
    public void handleErrPage(ParamException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        if (handleException(e, -3000, e.getMessage(), request, response)) {
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }

    private boolean handleException(CustomException e, int errcode, String msg, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        LOGGER.error("handleException errcode", e);
        if ((e != null && !e.isCheckErrHandle()) || handleJsonAccept(request)) {
            handleJsonResponse(response, GsonUtil.toJson(ResultEntity.newResultEntity(errcode, msg, null)));
            return true;
        }
        return false;
    }

    private boolean handleException(CustomException e, ResultEntity resultEntity, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        LOGGER.error("handleException", e);
        if ((e != null && !e.isCheckErrHandle()) || handleJsonAccept(request)) {
            handleJsonResponse(response, GsonUtil.toJson(resultEntity));
            return true;
        }
        return false;
    }

    private boolean handleJsonAccept(HttpServletRequest request) {
        if (isApiRequest(request)) {
            return true;
        }

        return AjaxUtil.isAjaxRequest(request);
    }

    private void handleJsonResponse(HttpServletResponse response, String ret) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(ret);
        writer.flush();

    }

    public static boolean isApiRequest(HttpServletRequest request) {
        return JSON_ACCEPT_URI_PATTERN.matcher(request.getPathInfo()).matches();
    }
}
